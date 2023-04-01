package gs25.hotel.reservation.management.system.entity.hotel;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class HotelReservation {
    public int idx;
    public int userIdx;
    public int hotelIdx;
    public int hotelRoomIdx;
    public String checkInDate;
    public String checkOutDate;
    public int adult;
    public int child;
    public int baby;
    public int extraBed;
    public int totalPrice;
    public String status;
    public String createdAt;
    public String updatedAt;

    public HotelReservation() {

    }

    public static HotelReservation builder() {
        return new HotelReservation();
    }

    public HotelReservation build() {
        return this;
    }




}
