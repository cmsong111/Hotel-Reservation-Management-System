package gs25.hotel.reservation.management.system.configuration;

import gs25.hotel.reservation.management.system.entity.User;
import gs25.hotel.reservation.management.system.observer.LoginStatus;
import gs25.hotel.reservation.management.system.repository.UserRepository;
import gs25.hotel.reservation.management.system.service.UserService;
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
    public UserService userService ;
    public User loginUser;
    public LoginStatus loginStatus;

    public void init() {
        userRepository = new UserRepository();
        userService = new UserService();
        loginUser = null;
        loginStatus = new LoginStatus();
    }
}
