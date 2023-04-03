package ds25.hotel.reservation.management.system.service.hotel;

import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.entity.hotel.HotelReservation;
import ds25.hotel.reservation.management.system.entity.hotel.HotelReservationState;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class HotelReservationServiceTest {
    private static Singleton singleton = Singleton.getInstance();
    private HotelReservationService hotelReservationService = Singleton.getInstance().getHotelReservationService();

    private HotelReservation makeHotelReservation() {
        return HotelReservation.builder()
                .idx(0)
                .hotelIdx(1)
                .hotelRoomIdx(1)
                .hotelRoomIdx(1)
                .checkInDate(new Date(123, Calendar.APRIL, 1, 0, 0, 0))
                .checkOutDate(new Date(123, Calendar.APRIL, 2, 0, 0, 0))
                .peopleCount(2)
                .totalPrice(10000)
                .status(HotelReservationState.BOOKED)
                .build();
    }

    @BeforeAll
    static void initialize() {
        singleton.init();
    }

    @Test
    void addHotelReservation() throws IOException {
        HotelReservation hotelReservation = makeHotelReservation();
        HotelReservation saved = hotelReservationService.addHotelReservation(hotelReservation);

        hotelReservation.setIdx(saved.getIdx());

        assertEquals(saved, hotelReservation);

    }

    @Test
    void updateHotelReservation() {
    }

    @Test
    void removeHotelReservation() {
    }

    @Test
    void getHotelReservation() {
    }

    @Test
    void getHotelReservationByHotelRoomIdx() {
    }

    @Test
    void getHotelReservationByUserId() {
    }

    @Test
    void getHotelReservationByHotelId() {
    }

    @Test
    void getUsingRoomCount() {
    }
}
