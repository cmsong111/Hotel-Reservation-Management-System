package ds25.hotel.reservation.management.system.service.hotel;

import com.google.gson.Gson;
import ds25.hotel.reservation.management.system.dto.hotel.HotelReviewDto;
import ds25.hotel.reservation.management.system.entity.hotel.Hotel;
import ds25.hotel.reservation.management.system.entity.hotel.HotelReview;
import ds25.hotel.reservation.management.system.entity.hotel.HotelReviewImage;
import ds25.hotel.reservation.management.system.entity.user.User;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelReviewImageRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelReviewRepository;
import ds25.hotel.reservation.management.system.repository.user.UserRepository;
import ds25.hotel.reservation.management.system.util.AggregateImpl;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HotelReviewService {
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final HotelReviewRepository hotelReviewRepository;
    private final HotelReviewImageRepository hotelReviewImageRepository;

    Gson gson = new Gson();
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public HotelReviewService(HotelReviewRepository hotelReviewRepository, HotelReviewImageRepository hotelReviewImageRepository, HotelRepository hotelRepository,
                              UserRepository userRepository) {
        this.hotelReviewRepository = hotelReviewRepository;
        this.hotelReviewImageRepository = hotelReviewImageRepository;
        this.hotelRepository = hotelRepository;
        this.userRepository = userRepository;
    }

    public AggregateImpl getHotelReviewListByHotelIdx(Long hotelIdx) {
        List<HotelReview> hotelReviewList = hotelReviewRepository.findByHotel_Idx(hotelIdx);
        AggregateImpl aggregate = new AggregateImpl(hotelReviewList.size());
        for (HotelReview hotelReview : hotelReviewList) {
            aggregate.appendObject(modelMapper.map(hotelReview, HotelReviewDto.class));
        }
        return aggregate;
    }

    public Long addHotelReviewImage(Long hotelReviewIdx, String imageUrl) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelReviewIdx);
        if (hotel.isEmpty()) {
            throw new RuntimeException("Hotel not found");
        }
        HotelReviewImage hotelReviewImage = HotelReviewImage.builder()
                .imageUrl(imageUrl)
                .build();

        return hotelReviewImageRepository.save(hotelReviewImage).getIdx();
    }

    public HotelReviewDto addHotelReview(HotelReviewDto hotelReviewDto) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelReviewDto.getHotelIdx());
        Optional<User> user = userRepository.findById(hotelReviewDto.getUserId());

        if (hotel.isEmpty()) {
            throw new RuntimeException("Hotel not found");
        } else if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        ArrayList<HotelReviewImage> hotelReviewImages = new ArrayList<>();
        for (Long hotelReviewImageIdx : hotelReviewDto.getImagesImageUrl()) {
            Optional<HotelReviewImage> hotelReviewImage = hotelReviewImageRepository.findById(hotelReviewImageIdx);
            hotelReviewImages.add(hotelReviewImage.get());
        }


        HotelReview hotelReview = HotelReview.builder()
                .hotel(hotel.get())
                .user(user.get())
                .content(hotelReviewDto.getContent())
                .rating(hotelReviewDto.getRating())
                .images(hotelReviewImages)
                .reply(null)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .build();
        return modelMapper.map(hotelReviewRepository.save(hotelReview), HotelReviewDto.class);
    }

    public void replyToReview(Long HotelReviewIdx, String reply) {
        log.info("replyToReview: " + HotelReviewIdx + ", " + reply);
        Optional<HotelReview> hotelReview = hotelReviewRepository.findById(HotelReviewIdx);
        if (hotelReview.isEmpty()) {
            throw new RuntimeException("Hotel Review not found");
        }
        hotelReview.get().setReply(reply);

        hotelReviewRepository.save(hotelReview.get());
        log.info("reply saved");
    }

    public ArrayList<HotelReviewDto> findReviewByUser(String userId) {
        log.info("findReviewByUser: " + userId);
        List<HotelReview> hotelReviewList = hotelReviewRepository.findByUser_Id(userId);
        ArrayList<HotelReviewDto> hotelReviewDtoList = new ArrayList<>();
        for (HotelReview hotelReview : hotelReviewList) {
            hotelReviewDtoList.add(modelMapper.map(hotelReview, HotelReviewDto.class));
        }
        log.info("Review found by user complete");
        return hotelReviewDtoList;
    }
}
