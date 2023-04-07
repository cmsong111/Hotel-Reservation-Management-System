package ds25.hotel.reservation.management.system.repository.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.HotelReviewImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelReviewImageRepository  extends JpaRepository<HotelReviewImage, Long> {
}