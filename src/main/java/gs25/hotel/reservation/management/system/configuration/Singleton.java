package gs25.hotel.reservation.management.system.configuration;

import gs25.hotel.reservation.management.system.entity.user.User;
import gs25.hotel.reservation.management.system.observer.LoginStatus;
import gs25.hotel.reservation.management.system.repository.hotel.HotelRepository;
import gs25.hotel.reservation.management.system.repository.hotel.HotelReservationRepository;
import gs25.hotel.reservation.management.system.repository.hotel.HotelRoomRepository;
import gs25.hotel.reservation.management.system.repository.user.UserRepository;
import gs25.hotel.reservation.management.system.service.user.UserService;
import lombok.Data;

@Data
public class Singleton {
    private static Singleton singleton = new Singleton();
    private Singleton() {}
    public static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
    public UserRepository userRepository ;
    public HotelRepository hotelRepository;
    public HotelRoomRepository hotelRoomRepository;
    public HotelReservationRepository hotelReservationRepository;
    public UserService userService ;
    public User loginUser;
    public LoginStatus loginStatus;

    public void init() {
        userRepository = new UserRepository();
        hotelRepository = new HotelRepository();
        hotelRoomRepository = new HotelRoomRepository();
        hotelReservationRepository = new HotelReservationRepository();

        userService = new UserService();

        loginUser = null;
        loginStatus = new LoginStatus();
    }
}
