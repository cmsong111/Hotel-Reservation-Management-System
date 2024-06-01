package ds25.hotel.reservation.management.system.domain.review.application;

import ds25.hotel.reservation.management.system.di.Service;
import ds25.hotel.reservation.management.system.domain.hotel.Hotel;
import ds25.hotel.reservation.management.system.domain.hotel.HotelRepository;
import ds25.hotel.reservation.management.system.domain.review.Review;
import ds25.hotel.reservation.management.system.domain.review.ReviewRepository;
import ds25.hotel.reservation.management.system.domain.user.User;
import ds25.hotel.reservation.management.system.domain.user.UserRepository;
import ds25.hotel.reservation.management.system.ui.dto.hotel.HotelReviewDto;
import ds25.hotel.reservation.management.system.ui.dto.hotel.HotelReviewImageDto;
import jakarta.inject.Inject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReviewService {
	private final UserRepository userRepository;
	private final HotelRepository hotelRepository;
	private final ReviewRepository reviewRepository;


	@Inject
	public ReviewService(ReviewRepository reviewRepository, HotelRepository hotelRepository,
						 UserRepository userRepository) {
		this.reviewRepository = reviewRepository;
		this.hotelRepository = hotelRepository;
		this.userRepository = userRepository;
	}

	/**
	 * 호텔 별 리뷰 리스트 조회
	 *
	 * @param hotelIdx 호텔 인덱스
	 * @return 호텔 리뷰 리스트
	 * @author 김남주
	 */
	public List<Review> getHotelReviewListByHotelIdx(Long hotelIdx) {
		//		AggregateImpl aggregate = new AggregateImpl(reviewList.size());
//		for (Review review : reviewList) {
//			aggregate.appendObject(convertToDto(review));
//		}
		return reviewRepository.findByHotel(hotelIdx);
	}


	/**
	 * 호텔 리뷰 추가
	 *
	 * @param hotelReviewDto 호텔 리뷰 DTO
	 * @return 호텔 리뷰 DTO
	 * @author 김남주
	 */
	public HotelReviewDto addHotelReview(HotelReviewDto hotelReviewDto) throws IllegalArgumentException {
		log.info("리뷰 추가: " + hotelReviewDto.toString());
		Optional<Hotel> hotel = hotelRepository.findById(hotelReviewDto.getHotelIdx());
		Optional<User> user = userRepository.findById(hotelReviewDto.getUserId());

		if (hotel.isEmpty()) {
			throw new IllegalArgumentException("Hotel not found");
		} else if (user.isEmpty()) {
			throw new IllegalArgumentException("User not found");
		}

		Review review = Review.builder()
				.hotelId(hotelReviewDto.getHotelIdx())
				.authorId(hotelReviewDto.getUserId())
				.content(hotelReviewDto.getContent())
				.rating(hotelReviewDto.getRating())
				.reply(null)
				.createdAt(new Timestamp(System.currentTimeMillis()))
				.updatedAt(new Timestamp(System.currentTimeMillis()))
				.build();

		Review reviewReview = reviewRepository.save(review);

		if (hotelReviewDto.getImages() != null) {
			for (HotelReviewImageDto hotelReviewImageDto : hotelReviewDto.getImages()) {
				HotelReviewImage hotelReviewImage = HotelReviewImage.builder()
						.review(reviewReview)
						.image(hotelReviewImageDto.getImage())
						.build();
				hotelReviewImageRepository.save(hotelReviewImage);
			}
		}
		log.info("리뷰 추가 완료: " + reviewReview.toString());
		return convertToDto(reviewReview);
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
		Optional<Review> hotelReview = reviewRepository.findById(HotelReviewIdx);
		if (hotelReview.isEmpty()) {
			throw new RuntimeException("Hotel Review not found");
		}
		hotelReview.get().setReply(reply);

		reviewRepository.save(hotelReview.get());
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
		List<Review> reviewList = reviewRepository.findByUser_Id(userId);
		ArrayList<HotelReviewDto> hotelReviewDtoList = new ArrayList<>();
		for (Review review : reviewList) {
			hotelReviewDtoList.add(convertToDto(review));
		}
		log.info("Review found by user complete");
		return hotelReviewDtoList;
	}


	/**
	 * 호텔 리뷰 삭제
	 *
	 * @param hotelReviewIdx 호텔 리뷰 인덱스
	 * @throws IllegalArgumentException 호텔 리뷰 정보가 없을 경우
	 * @author 김남주
	 */
	public void deleteHotelReview(Long hotelReviewIdx) throws IllegalArgumentException {
		log.info("deleteHotelReview: " + hotelReviewIdx);
		Optional<Review> hotelReview = reviewRepository.findById(hotelReviewIdx);
		if (hotelReview.isEmpty()) {
			throw new IllegalArgumentException("Hotel Review not found");
		}
		reviewRepository.delete(hotelReview.get());
		log.info("Hotel Review deleted");
	}

	public HotelReviewDto convertToDto(Review review) {
		HotelReviewDto hotelReviewDto = modelMapper.map(review, HotelReviewDto.class);
		ArrayList<HotelReviewImageDto> hotelReviewImageDtoList = new ArrayList<>();
		for (HotelReviewImage hotelReviewImage : hotelReviewImageRepository.findByReview_Idx(review.getIdx())) {
			hotelReviewImageDtoList.add(modelMapper.map(hotelReviewImage, HotelReviewImageDto.class));
		}
		hotelReviewDto.setImages(hotelReviewImageDtoList);
		return hotelReviewDto;
	}

	public String getHotelName(Long idx) {
		hotelRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
		return hotelRepository.findById(idx).get().getName();
	}

}
