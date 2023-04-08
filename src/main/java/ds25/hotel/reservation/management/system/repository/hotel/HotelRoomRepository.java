package ds25.hotel.reservation.management.system.repository.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.Hotel;
import ds25.hotel.reservation.management.system.entity.hotel.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom, Long> {
    long deleteByHotel(Hotel hotel);

    List<HotelRoom> findByHotel_Idx(Long idx);

    List<HotelRoom> findByHotel_IdxAndNameContains(Long idx, String name);
}


