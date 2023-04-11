package ds25.hotel.reservation.management.system.entity.hotel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long idx;
    private Long roomNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    private HotelRoomType roomType;
    private Long payedPrice;
}

