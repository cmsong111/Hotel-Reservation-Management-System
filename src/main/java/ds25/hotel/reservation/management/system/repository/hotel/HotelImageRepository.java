package ds25.hotel.reservation.management.system.repository.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelImageRepository extends JpaRepository<HotelImage, Long> {
}
