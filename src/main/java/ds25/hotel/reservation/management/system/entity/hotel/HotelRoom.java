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
public class HotelRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @ManyToOne
    private Hotel hotel;
    private String name;
    private String description;
    private int price;
    private int discount;
    private int roomCount;
    private int peopleCount;
    private BedSize bedSize;
    private int roomSize;
    @OneToMany
    private ArrayList<HotelImage> images;
    private ArrayList<HotelService> service;
}
