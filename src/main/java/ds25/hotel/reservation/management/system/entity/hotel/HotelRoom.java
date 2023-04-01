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
    private int hotelIdx;
    private String name;
    private String description;
    private int price;
    private int discount;
    private int roomCount;
    private int peopleCount;
    private int bedCount;
    private int roomSize;
    private ArrayList<String> image;
    private ArrayList<HotelService> service;
}
