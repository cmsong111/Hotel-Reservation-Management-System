package ds25.hotel.reservation.management.system.repository.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.HotelRoomTypeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRoomTypeImageRepository extends JpaRepository<HotelRoomTypeImage, Long> {
    List<HotelRoomTypeImage> findByRoomType_Idx(Long idx);

}
