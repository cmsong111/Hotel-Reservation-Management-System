package ds25.hotel.reservation.management.system.repository.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.Hotel;
import ds25.hotel.reservation.management.system.entity.hotel.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelImageRepository extends JpaRepository<HotelImage, Long> {
    List<HotelImage> findByHotel_Idx(Long idx);

    long deleteByHotel(Hotel hotel);
}
