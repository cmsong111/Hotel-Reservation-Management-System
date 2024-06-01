package ds25.hotel.reservation.management.system.config;

import ds25.hotel.reservation.management.system.ui.dto.user.UserDto;
import ds25.hotel.reservation.management.system.util.provider.HotelReservationProvider;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class AppConfig {
    private static AppConfig appConfig = new AppConfig();

    private AppConfig() {
        log.info("Singleton 객체가 생성되었습니다.");
        hotelReservationProvider = new HotelReservationProvider();
    }

    public static AppConfig getInstance() {
        if (appConfig == null) {
            appConfig = new AppConfig();
        }
        return appConfig;
    }

    private UserDto admin;
    private UserDto user;
    private HotelReservationProvider hotelReservationProvider;
}
