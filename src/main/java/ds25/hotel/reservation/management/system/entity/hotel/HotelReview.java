package ds25.hotel.reservation.management.system.entity.hotel;


import java.util.ArrayList;
import java.util.Date;

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
