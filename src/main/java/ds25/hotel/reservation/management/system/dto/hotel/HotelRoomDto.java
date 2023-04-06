package ds25.hotel.reservation.management.system.dto.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.BedSize;
import ds25.hotel.reservation.management.system.entity.hotel.Hotel;
import ds25.hotel.reservation.management.system.entity.hotel.HotelRoomImage;
import ds25.hotel.reservation.management.system.entity.hotel.HotelService;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelRoomDto {
    private Long idx;
    private Long hotelIdx;
    private String name;
    private String description;
    private int price;
    private int discount;
    private int roomCount;
    private int peopleCount;
    private BedSize bedSize;
    private int roomSize;
    private ArrayList<String> images;
    private ArrayList<HotelService> service;
}
