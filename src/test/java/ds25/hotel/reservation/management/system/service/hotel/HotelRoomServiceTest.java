package ds25.hotel.reservation.management.system.service.hotel;

import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.entity.hotel.BedSize;
import ds25.hotel.reservation.management.system.entity.hotel.HotelRoom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HotelRoomServiceTest {

    static Singleton singleton = Singleton.getInstance();
    HotelRoomService hotelRoomService = singleton.getHotelRoomService();

    @BeforeAll
    static void initialize() {
        singleton.init();
    }

    @Test
    void addHotelRoom() throws IOException {
        HotelRoom hotelRoom = HotelRoom.builder().idx(0).hotelIdx(1).name("test").description("test").price(100000).discount(0).roomCount(1).peopleCount(2).bedSize(BedSize.DOUBLE).roomSize(20).image(new ArrayList<String>() {{
            add("test");
        }}).service(null).build();
        HotelRoom saved = hotelRoomService.addHotelRoom(hotelRoom);

        Optional<HotelRoom> found = hotelRoomService.getHotelRoom(saved.getIdx());

        assertEquals(saved, found.get());
    }

    @Test
    void addHotelRoomFail() throws IOException {
        HotelRoom hotelRoom = HotelRoom.builder().idx(2).hotelIdx(1).name("test").description("test").price(100000).discount(0).roomCount(1).peopleCount(2).bedSize(BedSize.DOUBLE).roomSize(20).image(new ArrayList<String>() {{
            add("test");
        }}).service(null).build();
        HotelRoom saved = hotelRoomService.addHotelRoom(hotelRoom);

        assertNull(saved);
    }


    @Test
    void updateHotelRoom() throws IOException {
        HotelRoom hotelRoom = HotelRoom.builder().idx(0).hotelIdx(1).name("test").description("test").price(100000).discount(0).roomCount(1).peopleCount(2).bedSize(BedSize.DOUBLE).roomSize(20).image(new ArrayList<String>() {{
            add("test");
        }}).service(null).build();
        HotelRoom saved = hotelRoomService.addHotelRoom(hotelRoom);

        hotelRoom.setRoomSize(30);
        hotelRoom.setName("test2");
        hotelRoomService.updateHotelRoom(hotelRoom);

        Optional<HotelRoom> found = hotelRoomService.getHotelRoom(saved.getIdx());

        assertEquals(hotelRoom, found.get());
    }

    @Test
    void removeHotelRoom() throws Exception {
        HotelRoom hotelRoom = HotelRoom.builder().idx(0).hotelIdx(1).name("test").description("test").price(100000).discount(0).roomCount(1).peopleCount(2).bedSize(BedSize.DOUBLE).roomSize(20).image(new ArrayList<String>() {{
            add("test");
        }}).service(null).build();
        HotelRoom saved = hotelRoomService.addHotelRoom(hotelRoom);

        hotelRoomService.removeHotelRoom(saved.getIdx());

        Optional<HotelRoom> found = hotelRoomService.getHotelRoom(saved.getIdx());

        assertEquals(Optional.empty(), found);
    }

    @Test
    void removeHotelRoomFail() throws Exception {
        HotelRoom hotelRoom = HotelRoom.builder().idx(0).hotelIdx(1).name("test").description("test").price(100000).discount(0).roomCount(1).peopleCount(2).bedSize(BedSize.DOUBLE).roomSize(20).image(new ArrayList<String>() {{
            add("test");
        }}).service(null).build();
        HotelRoom saved = hotelRoomService.addHotelRoom(hotelRoom);

        Assertions.assertThrows(Exception.class, () -> {
            hotelRoomService.removeHotelRoom(saved.getIdx() + 1);

        });
    }

    @Test
    void getHotelRoom() throws IOException {
        HotelRoom hotelRoom = HotelRoom.builder().idx(0).hotelIdx(1).name("test").description("test").price(100000).discount(0).roomCount(1).peopleCount(2).bedSize(BedSize.DOUBLE).roomSize(20).image(new ArrayList<String>() {{
            add("test");
        }}).service(null).build();
        HotelRoom saved = hotelRoomService.addHotelRoom(hotelRoom);

        Optional<HotelRoom> found = hotelRoomService.getHotelRoom(saved.getIdx());

        assertEquals(saved, found.get());
    }

    @Test
    void getHotelRoomFail() throws IOException {
        HotelRoom hotelRoom = HotelRoom.builder().idx(0).hotelIdx(1).name("test").description("test").price(100000).discount(0).roomCount(1).peopleCount(2).bedSize(BedSize.DOUBLE).roomSize(20).image(new ArrayList<String>() {{
            add("test");
        }}).service(null).build();
        HotelRoom saved = hotelRoomService.addHotelRoom(hotelRoom);

        Optional<HotelRoom> found = hotelRoomService.getHotelRoom(saved.getIdx() + 1);

        assertEquals(found, Optional.empty());
    }
}