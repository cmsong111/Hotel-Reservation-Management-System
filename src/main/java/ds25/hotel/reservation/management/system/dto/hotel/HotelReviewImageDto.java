package ds25.hotel.reservation.management.system.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelReviewImageDto {
    private Long idx;
    private String image;
}
