package ds25.hotel.reservation.management.system.domain.reservation;

import java.sql.Timestamp;
import java.util.List;

public interface ReservationRepository {
	List<Reservation> findAll();

	List<Reservation> findByHotelRoom_Idx(Long idx);

	List<Reservation> findByHotelRoomType_Hotel_Idx(Long idx);

	List<Reservation> findByHotelRoom_RoomType_Hotel_Idx(Long idx);


	List<Reservation> findByUser_Id(String id);

	long countByCheckInDateBetweenAndCheckOutDateBetweenAndHotelRoom_RoomType_Idx(Timestamp checkInDateStart, Timestamp checkInDateEnd, Timestamp checkOutDateStart, Timestamp checkOutDateEnd, Long idx);

	boolean existsByHotelRoomType_IdxAndCheckInDateBetweenAndCheckOutDateBetween(Long idx, Timestamp checkInDateStart, Timestamp checkInDateEnd, Timestamp checkOutDateStart, Timestamp checkOutDateEnd);


}
