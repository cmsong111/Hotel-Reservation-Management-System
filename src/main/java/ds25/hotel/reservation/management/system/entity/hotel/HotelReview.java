package ds25.hotel.reservation.management.system.entity.hotel;


import ds25.hotel.reservation.management.system.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class HotelReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx;
    @ManyToOne
    Hotel hotel;
    @ManyToOne
    User user;
    String content;
    int rating;
    @OneToMany
    ArrayList<HotelReviewImage> images;
    String reply;
    Timestamp createdAt;
    Timestamp updatedAt;
}
