package ds25.hotel.reservation.management.system.entity.hotel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class HotelReviewImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx;
    String imageUrl;
    @ManyToOne
    HotelReview review;
}
