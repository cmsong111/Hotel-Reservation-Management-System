package ds25.hotel.reservation.management.system.domain.reservation;

import ds25.hotel.reservation.management.system.domain.hotel.HotelRoom;
import ds25.hotel.reservation.management.system.domain.hotel.HotelRoomType;
import ds25.hotel.reservation.management.system.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    private HotelRoomType hotelRoomType;
    @ManyToOne(fetch = FetchType.EAGER)
    private HotelRoom hotelRoom;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private long peopleCount;
    private long totalPrice;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int payedMoney;
    private String memo;
}
