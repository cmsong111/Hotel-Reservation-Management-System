package ds25.hotel.reservation.management.system.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelReservationDto {
    private long idx;
    private String userId;
    private long hotelIdx;
    private long hotelRoomTypeIdx;
    private long hotelRoomIdx;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private long peopleCount;
    private long totalPrice;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int payedMoney;
    private String memo;
}
