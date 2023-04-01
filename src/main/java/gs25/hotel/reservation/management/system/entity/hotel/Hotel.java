package gs25.hotel.reservation.management.system.entity.hotel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {
    private int idx;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String description;
    private ArrayList<String> image;
    private ArrayList<HotelService> service;
}
