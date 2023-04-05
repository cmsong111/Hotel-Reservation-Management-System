package ds25.hotel.reservation.management.system.entity.hotel;

import ds25.hotel.reservation.management.system.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;


@Entity
@Getter
@Setter
@ToString
public class HotelReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;
    @ManyToOne
    private User user;
    @ManyToOne
    private Hotel hotel;
    @ManyToOne
    private HotelRoom hotelRoom;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private long peopleCount;
    private long totalPrice;
    private HotelReservationState status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public HotelReservation() {

    }

    public static HotelReservation builder() {
        return new HotelReservation();
    }

    public HotelReservation idx(int idx) {
        this.idx = idx;
        return this;
    }

    public HotelReservation user(User user) {
        this.user = user;
        return this;
    }


    public HotelReservation hotel(Hotel hotel) {
        this.hotel = hotel;
        return this;
    }
    public HotelReservation hotelRoom(HotelRoom hotelRoom) {
        this.hotelRoom = hotelRoom;
        return this;
    }
    public HotelReservation checkInDate(Timestamp checkInDate) {
        this.checkInDate = checkInDate;
        return this;
    }

    public HotelReservation checkOutDate(Timestamp checkOutDate) {
        this.checkOutDate = checkOutDate;
        return this;
    }

    public HotelReservation totalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public HotelReservation status(HotelReservationState status) {
        this.status = status;
        return this;
    }

    public HotelReservation createdAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }
    public HotelReservation peopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
        return this;
    }

    public HotelReservation updatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public HotelReservation build() {
        return this;
    }
}
