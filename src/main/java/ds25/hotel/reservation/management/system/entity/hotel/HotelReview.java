package ds25.hotel.reservation.management.system.entity.hotel;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelReview  {
    int idx;
    int hotelIdx;
    int userIdx;
    String content;
    int rating;
    ArrayList<String> images;
    String reply;
    Date createdAt;
    Date updatedAt;
}
