package ds25.hotel.reservation.management.system.repository.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.Hotel;
import ds25.hotel.reservation.management.system.entity.hotel.HotelReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface HotelReservationRepository extends JpaRepository<HotelReservation, Long> {
    List<HotelReservation> findAll();

    List<HotelReservation> findByHotelRoom_IdxAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(Long idx, Timestamp checkInDate, Timestamp checkOutDate);

    List<HotelReservation> findByHotelRoom_Hotel_Idx(Long idx);

    List<HotelReservation> findByUser_Id(String id);
}
