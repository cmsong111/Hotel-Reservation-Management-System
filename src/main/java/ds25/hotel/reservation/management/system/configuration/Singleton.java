package ds25.hotel.reservation.management.system.configuration;

import ds25.hotel.reservation.management.system.repository.hotel.HotelRepositoryImpl;
import ds25.hotel.reservation.management.system.repository.hotel.HotelReservationRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRoomRepository;
import ds25.hotel.reservation.management.system.provider.UserProvider;
import ds25.hotel.reservation.management.system.service.hotel.HotelReservationService;
import ds25.hotel.reservation.management.system.service.hotel.HotelRoomService;
import ds25.hotel.reservation.management.system.service.hotel.HotelService;
import ds25.hotel.reservation.management.system.service.user.UserService;
import lombok.Data;

@Data
public class Singleton {
    private static Singleton singleton = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    public UserProvider userProvider;

    public void init() {
        userProvider = new UserProvider();
    }
}
