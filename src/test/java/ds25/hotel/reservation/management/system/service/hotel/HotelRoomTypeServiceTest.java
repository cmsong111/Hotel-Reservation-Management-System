package ds25.hotel.reservation.management.system.service.hotel;

import ds25.hotel.reservation.management.system.configuration.JpaConfig;
import ds25.hotel.reservation.management.system.dto.hotel.HotelRoomTypeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(classes = JpaConfig.class)
class HotelRoomTypeServiceTest {

    @Autowired
    HotelRoomTypeService hotelRoomTypeService;

    @Test
    void addHotelRoomType() {
        Optional<HotelRoomTypeDto> hotel =  hotelRoomTypeService.findHotelRoomByIdx(1L);
        System.out.println(hotel.get().getName());
    }
}

