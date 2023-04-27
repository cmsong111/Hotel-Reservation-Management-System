package ds25.hotel.reservation.management.system.entity.hotel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class HotelRoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<HotelRoomTypeImage> images;
    private List<HotelServiceEnum> service;
}
