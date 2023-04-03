package ds25.hotel.reservation.management.system.service.hotel;

import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.entity.hotel.Hotel;
import ds25.hotel.reservation.management.system.entity.hotel.HotelReservation;
import ds25.hotel.reservation.management.system.entity.hotel.HotelReservationState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

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
    void addHotelReservation() throws Exception {
        HotelReservation hotelReservation = makeHotelReservation();
        HotelReservation saved = hotelReservationService.addHotelReservation(hotelReservation);

        hotelReservation.setIdx(saved.getIdx());

        assertEquals(saved, hotelReservation);
    }

    @Test
    void addHotelReservationFail() throws Exception {
        HotelReservation hotelReservation = makeHotelReservation();
        HotelReservation saved = hotelReservationService.addHotelReservation(hotelReservation);

        hotelReservation.setIdx(1);

        Assertions.assertThrowsExactly(Exception.class, () -> hotelReservationService.addHotelReservation(hotelReservation));
    }

    @Test
    void updateHotelReservation() throws Exception {
        HotelReservation hotelReservation = makeHotelReservation();
        HotelReservation saved = hotelReservationService.addHotelReservation(hotelReservation);

        hotelReservation.setIdx(saved.getIdx());
        hotelReservation.setPeopleCount(3);
        hotelReservationService.updateHotelReservation(hotelReservation);

        HotelReservation found = hotelReservationService.getHotelReservation(saved.getIdx());

        assertEquals(hotelReservation.getHotelIdx(), found.getHotelIdx());
        assertEquals(hotelReservation.getHotelRoomIdx(), found.getHotelRoomIdx());
        assertEquals(hotelReservation.getCheckInDate(), found.getCheckInDate());
        assertEquals(hotelReservation.getCheckOutDate(), found.getCheckOutDate());
        assertEquals(hotelReservation.getPeopleCount(), found.getPeopleCount());
        assertEquals(hotelReservation.getTotalPrice(), found.getTotalPrice());
        assertEquals(hotelReservation.getStatus(), found.getStatus());
    }

    @Test
    void removeHotelReservation() throws Exception {
        HotelReservation hotelReservation = makeHotelReservation();
        HotelReservation saved = hotelReservationService.addHotelReservation(hotelReservation);
        hotelReservationService.removeHotelReservation(saved.getIdx());
        Assertions.assertThrows(Exception.class, () -> hotelReservationService.removeHotelReservation(saved.getIdx()));
    }

    @Test
    void removeHotelReservationFail() throws Exception {
        HotelReservation hotelReservation = makeHotelReservation();
        HotelReservation saved = hotelReservationService.addHotelReservation(hotelReservation);
        Assertions.assertThrows(Exception.class, () -> hotelReservationService.removeHotelReservation(saved.getIdx() + 2));
    }

    @Test
    void getHotelReservation() throws Exception {
        HotelReservation hotelReservation = hotelReservationService.getHotelReservation(1);

        assertEquals(1, hotelReservation.getIdx());
        assertEquals(1, hotelReservation.getHotelIdx());
        assertEquals(1, hotelReservation.getHotelRoomIdx());
        assertEquals(2, hotelReservation.getUserIdx());
        assertEquals(new Date(123, Calendar.APRIL, 3, 3, 0, 0), hotelReservation.getCheckInDate());
        assertEquals(new Date(123, Calendar.APRIL, 4, 11, 0, 0), hotelReservation.getCheckOutDate());
        assertEquals(2, hotelReservation.getPeopleCount());
        assertEquals(100000, hotelReservation.getTotalPrice());
        assertEquals(HotelReservationState.CHECKED_OUT, hotelReservation.getStatus());
        assertEquals(new Date(123, Calendar.APRIL, 2, 3, 0, 0), hotelReservation.getCreatedAt());
        assertEquals(new Date(123, Calendar.APRIL, 4, 11, 0, 0), hotelReservation.getUpdatedAt());
    }

    @Test
    void getHotelReservationFail() throws Exception {
        Assertions.assertThrows(Exception.class, () -> hotelReservationService.getHotelReservation(100));
    }

    @Disabled("Not implemented yet")
    @Test
    void getHotelReservationByHotelRoomIdx() {
        // TODO: Implement
    }

    @Disabled("Not implemented yet")
    @Test
    void getHotelReservationByHotelRoomIdxFail() {
        // TODO: Implement
    }

    @Disabled("Not implemented yet")
    @Test
    void getHotelReservationByUserId() {
        // TODO: Implement
    }

    @Disabled("Not implemented yet")
    @Test
    void getHotelReservationByUserIdFail() {
        // TODO: Implement
    }

    @Disabled("Not implemented yet")
    @Test
    void getHotelReservationByHotelId() {
        // TODO: Implement
    }

    @Disabled("Not implemented yet")
    @Test
    void getHotelReservationByHotelIdFail() {
        // TODO: Implement
    }

    @Disabled("Not implemented yet")
    @Test
    void getUsingRoomCount() {
        // TODO: Implement
    }

    @Disabled("Not implemented yet")
    @Test
    void getUsingRoomCountFail() {
        // TODO: Implement
    }
}
