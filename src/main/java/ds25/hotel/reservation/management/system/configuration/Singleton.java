package ds25.hotel.reservation.management.system.configuration;

import ds25.hotel.reservation.management.system.dto.user.UserDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
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

    public UserDto admin;
    public UserDto user;


    public void init() {
        log.info("싱글톤 초기화");
    }
}
