package ds25.hotel.reservation.management.system.global.configuration;

import ds25.hotel.reservation.management.system.domain.user.dto.UserDto;
import ds25.hotel.reservation.management.system.provider.HotelReservationProvider;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Singleton {
    private static Singleton singleton = new Singleton();

    private Singleton() {
        log.info("Singleton 객체가 생성되었습니다.");
        hotelReservationProvider = new HotelReservationProvider();
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    private UserDto admin;
    private UserDto user;
    private HotelReservationProvider hotelReservationProvider;
}
