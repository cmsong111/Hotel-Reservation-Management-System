package ds25.hotel.reservation.management.system.dto.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.HotelImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelReviewDto {
    int idx;
    long hotelIdx;
    String userId;
    String content;
    int rating;
    private ArrayList<HotelImage> images;
    long reservationIdx;
    String reply;
    Timestamp createdAt;
    Timestamp updatedAt;
}
