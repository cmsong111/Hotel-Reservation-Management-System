package ds25.hotel.reservation.management.system.pattern.command;

import ds25.hotel.reservation.management.system.configuration.JpaConfig;
import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.dto.hotel.HotelReservationDto;
import ds25.hotel.reservation.management.system.dto.user.UserDto;
import ds25.hotel.reservation.management.system.pattern.facoryMethod.HotelReservationFactoryMethod;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = JpaConfig.class)
class HotelReservationCommandTest {
    HotelReservationCommand reservation;

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

    @BeforeEach
    void setUpEach() {
        reservation = new HotelReservationCommand(HotelReservationFactoryMethod.createReservation(1L, 1L));
        reservation.getHotelReservationDto().setCheckInDate(new Timestamp(System.currentTimeMillis()));
        reservation.getHotelReservationDto().setCheckOutDate(new Timestamp(System.currentTimeMillis() + 1000000));
        reservation.getHotelReservationDto().setPeopleCount(2);
    }

    @Test
    void pay() {
        reservation.pay(10000);

        assertEquals(10000, reservation.getHotelReservationDto().getPayedMoney());
    }

    @Test
    void cancel() {
        reservation.cancle();

        assertNull(reservation.getHotelReservationDto());
    }
}
