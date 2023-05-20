package ds25.hotel.reservation.management.system.pattern.strategy;

import ds25.hotel.reservation.management.system.dto.hotel.HotelDto;

import java.util.ArrayList;
import java.util.List;

public class BuildDescSort implements SortStrategy{
    @Override
    public void sorting(ArrayList<HotelDto> hotelList) {
        hotelList.sort((o1, o2) -> o2.getIdx().compareTo(o1.getIdx()));
    }
}
