package ds25.hotel.reservation.management.system.domain.hotel;

import java.util.List;


public interface HotelRoomTypeRepository {
	long deleteByHotel(Hotel hotel);

	List<HotelRoomType> findByHotel_Idx(Long idx);

	List<HotelRoomType> findByHotel_IdxAndNameContains(Long idx, String name);
}


