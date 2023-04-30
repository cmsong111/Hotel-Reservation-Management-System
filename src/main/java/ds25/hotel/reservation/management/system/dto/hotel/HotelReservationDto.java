package ds25.hotel.reservation.management.system.dto.hotel;

import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelReservationDto {
    private long idx;
    @Setter(AccessLevel.NONE)
    String userId;
    private long hotelIdx;
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
