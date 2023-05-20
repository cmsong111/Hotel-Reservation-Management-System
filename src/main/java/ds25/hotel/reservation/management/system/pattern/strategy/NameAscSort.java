package ds25.hotel.reservation.management.system.pattern.strategy;

import ds25.hotel.reservation.management.system.dto.hotel.HotelDto;

import java.util.ArrayList;

public class NameAscSort implements SortStrategy {
    @Override
    public void sorting(ArrayList<HotelDto> hotelList) {
        hotelList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
    }
}

