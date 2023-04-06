package ds25.hotel.reservation.management.system.repository.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom, Long> {

    List<HotelRoom> findAll();

    List<HotelRoom> findByHotel_Idx(Long idx);

    Optional<HotelRoom> findById(Long idx);
}

