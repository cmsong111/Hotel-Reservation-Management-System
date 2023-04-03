package ds25.hotel.reservation.management.system.entity.hotel;

import lombok.Data;

import java.util.Date;


@Data
public class HotelReservation {
    private int idx;
    private int userIdx;
    private int hotelIdx;
    private int hotelRoomIdx;
    private Date checkInDate;
    private Date checkOutDate;
    private int peopleCount;
    private int totalPrice;
    private HotelReservationState status;
    private Date createdAt;
    private Date updatedAt;

    public HotelReservation() {

    }

    public static HotelReservation builder() {
        return new HotelReservation();
    }

    public HotelReservation idx(int idx) {
        this.idx = idx;
        return this;
    }

    public HotelReservation userIdx(int userIdx) {
        this.userIdx = userIdx;
        return this;
    }

    public HotelReservation hotelIdx(int hotelIdx) {
        this.hotelIdx = hotelIdx;
        return this;
    }

    public HotelReservation hotelRoomIdx(int hotelRoomIdx) {
        this.hotelRoomIdx = hotelRoomIdx;
        return this;
    }

    public HotelReservation checkInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
        return this;
    }

    public HotelReservation checkOutDate(Date checkOutDate) {
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

    public HotelReservation createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }
    public HotelReservation peopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
        return this;
    }

    public HotelReservation updatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public HotelReservation build() {
        return this;
    }
}
