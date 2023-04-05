package ds25.hotel.reservation.management.system.service.hotel;

import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.repository.hotel.HotelReviewRepository;

public class HotelReviewService {
    HotelReviewRepository hotelReviewRepository = Singleton.getInstance().getHotelReviewRepository();

}
