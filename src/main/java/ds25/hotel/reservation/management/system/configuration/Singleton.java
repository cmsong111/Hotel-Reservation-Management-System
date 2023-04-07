package ds25.hotel.reservation.management.system.configuration;

import ds25.hotel.reservation.management.system.provider.UserProvider;
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
