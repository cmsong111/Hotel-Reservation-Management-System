package ds25.hotel.reservation.management.system.service.hotel;

import ds25.hotel.reservation.management.system.dto.hotel.HotelReviewDto;
import ds25.hotel.reservation.management.system.entity.hotel.Hotel;
import ds25.hotel.reservation.management.system.entity.hotel.HotelImage;
import ds25.hotel.reservation.management.system.entity.hotel.HotelReview;
import ds25.hotel.reservation.management.system.entity.user.User;
import ds25.hotel.reservation.management.system.repository.hotel.HotelImageRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRepository;
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
    private final HotelImageRepository hotelImageRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public HotelReviewService(HotelReviewRepository hotelReviewRepository, HotelRepository hotelRepository,
                              UserRepository userRepository, HotelImageRepository hotelImageRepository) {
        this.hotelReviewRepository = hotelReviewRepository;
        this.hotelRepository = hotelRepository;
        this.userRepository = userRepository;
        this.hotelImageRepository = hotelImageRepository;
    }

    /**
     * 호텔 별 리뷰 리스트 조회
     *
     * @param hotelIdx 호텔 인덱스
     * @return 호텔 리뷰 리스트
     * @author 김남주
     */
    public AggregateImpl getHotelReviewListByHotelIdx(Long hotelIdx) {
        List<HotelReview> hotelReviewList = hotelReviewRepository.findByHotel_Idx(hotelIdx);
        AggregateImpl aggregate = new AggregateImpl(hotelReviewList.size());
        for (HotelReview hotelReview : hotelReviewList) {
            aggregate.appendObject(modelMapper.map(hotelReview, HotelReviewDto.class));
        }
        return aggregate;
    }

    /**
     * 리뷰 이미지 추가
     *
     * @param HotelIdx 호텔 리뷰 인덱스
     * @param imageUrl 이미지 URL
     * @return 호텔 리뷰 이미지 인덱스
     * @author 김남주
     */
    public String addHotelReviewImage(String imageUrl) {
        HotelImage hotelImage = HotelImage.builder().image(imageUrl).build();
        return hotelImageRepository.save(hotelImage).getImage();
    }

    /**
     * 호텔 리뷰 추가
     *
     * @param hotelReviewDto 호텔 리뷰 DTO
     * @return 호텔 리뷰 DTO
     * @author 김남주
     */
    public HotelReviewDto addHotelReview(HotelReviewDto hotelReviewDto) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelReviewDto.getHotelIdx());
        Optional<User> user = userRepository.findById(hotelReviewDto.getUserId());

        if (hotel.isEmpty()) {
            throw new RuntimeException("Hotel not found");
        } else if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        for (HotelImage image : hotelReviewDto.getImages()) {
            Optional<HotelImage> found = hotelImageRepository.findByImage(image.getImage());
            if (found.isEmpty()) {
                hotelImageRepository.save(image);
            }
        }


        HotelReview hotelReview = HotelReview.builder()
                .hotel(hotel.get())
                .user(user.get())
                .content(hotelReviewDto.getContent())
                .rating(hotelReviewDto.getRating())
                .images(hotelReviewDto.getImages())
                .reply(null)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .build();
        return modelMapper.map(hotelReviewRepository.save(hotelReview), HotelReviewDto.class);
    }

    /**
     * 호텔 리뷰 답글 달기
     *
     * @param HotelReviewIdx 호텔 리뷰 인덱스
     * @param reply          답글 내용
     * @aothor 김남주
     */
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

    /**
     * 호텔 리뷰 유저별 조회
     *
     * @param userId 유저 아이디
     * @return 호텔 리뷰 리스트
     * @aothor 김남주
     */
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


    /**
     * 호텔 리뷰 삭제
     *
     * @param hotelReviewIdx 호텔 리뷰 인덱스
     * @aothor 김남주
     */
    public void deleteHotelReview(Long hotelReviewIdx) {
        Optional<HotelReview> hotelReview = hotelReviewRepository.findById(hotelReviewIdx);
        if (hotelReview.isEmpty()) {
            throw new RuntimeException("Hotel Review not found");
        }
        hotelReviewRepository.delete(hotelReview.get());
    }

}
