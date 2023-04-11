package ds25.hotel.reservation.management.system.entity.hotel;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long idx;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String description;
    @OneToMany(fetch = FetchType.EAGER)
    private List<HotelImage> images;
    private List<HotelServiceEnum> service;
}
