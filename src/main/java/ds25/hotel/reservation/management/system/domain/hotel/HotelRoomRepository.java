package ds25.hotel.reservation.management.system.domain.hotel;

import java.util.List;


public interface HotelRoomRepository  {
    List<HotelRoom> findByRoomType_Hotel_Idx(Long idx);
    List<HotelRoom> findByRoomType_Idx(Long idx);

    long countByRoomType_Idx(Long idx);

}
