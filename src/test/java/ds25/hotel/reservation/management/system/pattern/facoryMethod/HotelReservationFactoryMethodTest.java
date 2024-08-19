package ds25.hotel.reservation.management.system.pattern.facoryMethod;

import ds25.hotel.reservation.management.system.configuration.JpaConfig;
import ds25.hotel.reservation.management.system.global.configuration.Singleton;
import ds25.hotel.reservation.management.system.dto.hotel.HotelReservationDto;
import ds25.hotel.reservation.management.system.domain.user.dto.UserDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = JpaConfig.class)
class HotelReservationFactoryMethodTest {

    @BeforeAll
    static void setUp() {
        UserDto user = UserDto.builder()
                .id("user")
                .password("user")
                .name("user")
                .email("user@example.com")
                .build();
        Singleton singleton = Singleton.getInstance();
        singleton.setUser(user);
    }

    @Test
    void createReservation() {
        HotelReservationDto reservation = HotelReservationFactoryMethod.createReservation(1L, 1L);

        assertNotNull(reservation);
        assertNotEquals(0L, reservation.getIdx());
        assertEquals(reservation.getPayedMoney(),0);
    }
}
