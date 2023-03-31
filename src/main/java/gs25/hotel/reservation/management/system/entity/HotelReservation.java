package gs25.hotel.reservation.management.system.entity;

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

    @Override
    public String toString() {
        return "HotelReservation{" +
                "idx=" + idx +
                ", userIdx=" + userIdx +
                ", hotelIdx=" + hotelIdx +
                ", hotelRoomIdx=" + hotelRoomIdx +
                ", checkInDate='" + checkInDate + '\'' +
                ", checkOutDate='" + checkOutDate + '\'' +
                ", adult=" + adult +
                ", child=" + child +
                ", baby=" + baby +
                ", extraBed=" + extraBed +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }


}
