package ds25.hotel.reservation.management.system.configuration;

import ds25.hotel.reservation.management.system.repository.hotel.HotelRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelReservationRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRoomRepository;
import ds25.hotel.reservation.management.system.provider.UserProvider;
import ds25.hotel.reservation.management.system.repository.user.UserRepository;
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

    // Repository
    public UserRepository userRepository;
    public HotelRepository hotelRepository;
    public HotelRoomRepository hotelRoomRepository;
    public HotelReservationRepository hotelReservationRepository;

    // Service
    public UserService userService;
    public HotelService hotelService;

    // Provider
    public UserProvider userProvider;

    public void init() {
        userRepository = new UserRepository();
        hotelRepository = new HotelRepository();
        hotelRoomRepository = new HotelRoomRepository();
        hotelReservationRepository = new HotelReservationRepository();

        userService = new UserService();
        hotelService = new HotelService();

        userProvider = new UserProvider();
    }
}
