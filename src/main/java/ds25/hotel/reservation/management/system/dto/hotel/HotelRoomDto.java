package ds25.hotel.reservation.management.system.dto.hotel;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelRoomDto {
    private Long idx;
    private Long roomNumber;
    private Long roomTypeIdx;
    private Long payedPrice;
}
