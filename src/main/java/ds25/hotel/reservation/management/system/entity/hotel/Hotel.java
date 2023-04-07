package ds25.hotel.reservation.management.system.entity.hotel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String description;
    @OneToMany
    private ArrayList<HotelImage> images;
    private ArrayList<HotelService> service;
}
