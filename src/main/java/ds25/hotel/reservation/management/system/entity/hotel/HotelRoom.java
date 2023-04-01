package ds25.hotel.reservation.management.system.entity.hotel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelRoom {
    private int idx;
    private String name;
    private String description;
    private int price;
    private int count;
    private int maxPeople;
    private int maxExtraPeople;
    private int maxBed;
    private int maxExtraBed;
    private int roomSize;
    private boolean isSmoking;
    private int hotelIdx;
    private ArrayList<String> image;
    private ArrayList<HotelService> service;
}
