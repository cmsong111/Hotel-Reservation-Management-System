package ds25.hotel.reservation.management.system.dto.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.HotelReservationState;
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
    String userId;
    private long hotelIdx;
    private long hotelRoomIdx;
    // TODO : 추후 Swing 에서 쓰기 편한 객체로 변경
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private long peopleCount;
    private long totalPrice;
    private HotelReservationState status;
    // TODO : 추후 Swing 에서 쓰기 편한 객체로 변경
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
