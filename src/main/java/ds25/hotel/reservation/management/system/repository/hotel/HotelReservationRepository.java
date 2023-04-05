package ds25.hotel.reservation.management.system.repository.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.HotelReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface HotelReservationRepository extends JpaRepository<HotelReservation, Long> {


    List<HotelReservation> findAll();

    List<HotelReservation> findByHotelReservationsByRoomIdxAndTime(int roomIdx, Timestamp startDate, Timestamp endDate);

    List<HotelReservation> findByHotel_Idx(Long idx);

    List<HotelReservation> findByUser_Id(String id);
}
