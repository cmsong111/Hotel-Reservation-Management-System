package ds25.hotel.reservation.management.system.pattern.strategy;


import ds25.hotel.reservation.management.system.dto.hotel.HotelDto;

import java.util.ArrayList;

public interface SortStrategy {
    void sorting(ArrayList<HotelDto> hotelList);
}
