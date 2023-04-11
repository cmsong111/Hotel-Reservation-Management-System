package ds25.hotel.reservation.management.system.entity.hotel;


import ds25.hotel.reservation.management.system.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class HotelReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Long idx;
    @ManyToOne
    Hotel hotel;
    @ManyToOne
    User user;
    String content;
    int rating;
    @OneToMany(fetch = FetchType.EAGER)
    List<HotelImage> images;
    @OneToOne
    HotelReservation reservation;
    String reply;
    Timestamp createdAt;
    Timestamp updatedAt;
}
