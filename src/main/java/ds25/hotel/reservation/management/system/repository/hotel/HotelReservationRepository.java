package ds25.hotel.reservation.management.system.repository.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.HotelReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface HotelReservationRepository extends JpaRepository<HotelReservation, Long> {
    List<HotelReservation> findAll();

    List<HotelReservation> findByHotelRoom_Idx(Long idx);

    List<HotelReservation> findByHotelRoomType_Hotel_Idx(Long idx);

    List<HotelReservation> findByHotelRoom_RoomType_Hotel_Idx(Long idx);




    List<HotelReservation> findByUserEmail(String email);



    long countByCheckInDateBetweenAndCheckOutDateBetweenAndHotelRoom_RoomType_Idx(Timestamp checkInDateStart, Timestamp checkInDateEnd, Timestamp checkOutDateStart, Timestamp checkOutDateEnd, Long idx);

    boolean existsByHotelRoomType_IdxAndCheckInDateBetweenAndCheckOutDateBetween(Long idx, Timestamp checkInDateStart, Timestamp checkInDateEnd, Timestamp checkOutDateStart, Timestamp checkOutDateEnd);





}
