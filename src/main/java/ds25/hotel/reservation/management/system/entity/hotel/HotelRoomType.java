package ds25.hotel.reservation.management.system.entity.hotel;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class HotelRoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long idx;
    @ManyToOne(fetch = FetchType.EAGER)
    private Hotel hotel;
    private String name;
    private String description;
    private int price;
    private int discount;
    private int peopleCount;
    private BedSize bedSize;
    private int roomSize;
    @OneToMany(fetch = FetchType.EAGER)
    private List<HotelImage> images;
    private List<HotelServiceEnum> service;
}
