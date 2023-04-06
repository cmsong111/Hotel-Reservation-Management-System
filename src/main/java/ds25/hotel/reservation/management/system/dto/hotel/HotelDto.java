package ds25.hotel.reservation.management.system.dto.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.HotelService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {
    private Long idx;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String description;
    private ArrayList<String> HotelImages;
    private ArrayList<HotelService> service;
}
