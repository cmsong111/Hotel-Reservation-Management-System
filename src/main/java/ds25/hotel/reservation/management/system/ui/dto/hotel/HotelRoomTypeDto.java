package ds25.hotel.reservation.management.system.ui.dto.hotel;

import ds25.hotel.reservation.management.system.domain.hotel.BedType;
import ds25.hotel.reservation.management.system.domain.hotel.HotelAmenity;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelRoomTypeDto {
    private Long idx;
    private Long hotelIdx;
    private String name;
    private String description;
    private String image;
    private int price;
    private int discount;
    private int roomCount;
    private int peopleCount;
    private BedType bedType;
    private int roomSize;
    private ArrayList<HotelRoomTypeImageDto> images;
    private ArrayList<HotelAmenity> service;

}
