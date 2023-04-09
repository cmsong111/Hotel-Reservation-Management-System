package ds25.hotel.reservation.management.system.repository.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.Hotel;
import ds25.hotel.reservation.management.system.entity.hotel.HotelRoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRoomTypeRepository extends JpaRepository<HotelRoomType, Long> {
    long deleteByHotel(Hotel hotel);

    List<HotelRoomType> findByHotel_Idx(Long idx);

    List<HotelRoomType> findByHotel_IdxAndNameContains(Long idx, String name);
}


