package ds25.hotel.reservation.management.system.service.hotel;

import ds25.hotel.reservation.management.system.configuration.JpaConfig;
import ds25.hotel.reservation.management.system.dto.hotel.HotelReservationDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = JpaConfig.class)
@Slf4j
class HotelReservationServiceTest {

    @Autowired
    HotelReservationService hotelReservationService;


    @Test
    void getAvailableRoom1() {
        Timestamp newCheckin = Timestamp.valueOf("2023-04-09 14:00:00");
        log.info("new Check In time is : " + newCheckin.toLocalDateTime());
        Timestamp newCheckout = Timestamp.valueOf("2023-04-10 12:00:00"); //2023년 April 8일 Saturday PM 8:08:20
        log.info("new Check Out time is : " + newCheckout.toLocalDateTime());

        Long count = hotelReservationService.getAvailableRoom(1L, newCheckin, newCheckout);

        assertEquals(9L, count);
    }

    @Test
    void getAvailableRoom2() {
        Timestamp newCheckin = Timestamp.valueOf("2023-04-08 14:00:00");
        log.info("new Check In time is : " + newCheckin.toLocalDateTime());
        Timestamp newCheckout = Timestamp.valueOf("2023-04-10 12:00:00"); //2023년 April 8일 Saturday PM 8:08:20
        log.info("new Check Out time is : " + newCheckout.toLocalDateTime());

        Long count = hotelReservationService.getAvailableRoom(1L, newCheckin, newCheckout);

        assertEquals(9L, count);
    }

    @Test
    void getAvailableRoom3() {
        Timestamp newCheckin = Timestamp.valueOf("2023-04-08 14:00:00");
        log.info("new Check In time is : " + newCheckin.toLocalDateTime());
        Timestamp newCheckout = Timestamp.valueOf("2023-04-10 12:00:00"); //2023년 April 8일 Saturday PM 8:08:20
        log.info("new Check Out time is : " + newCheckout.toLocalDateTime());

        Long count = hotelReservationService.getAvailableRoom(10L, newCheckin, newCheckout);

        assertEquals(1L, count);
    }

    @Test
    void createReservationSuccess(){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        HotelReservationDto hotelReservationDto = HotelReservationDto.builder()
                .hotelRoomIdx(1L)
                .hotelIdx(1L)
                .checkInDate(time)
                .checkOutDate(time)
                .peopleCount(2L)
                .build();

        assertThrows(IllegalArgumentException.class, () -> hotelReservationService.createHotelReservation(hotelReservationDto));
    }
}
