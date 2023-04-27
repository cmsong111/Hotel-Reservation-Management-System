package ds25.hotel.reservation.management.system.repository.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.HotelReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelReviewImageRepository extends JpaRepository<HotelReviewImage, Long> {
    List<HotelReviewImage> findByReview_Idx(Long idx);

}
