package ds25.hotel.reservation.management.system;

import ds25.hotel.reservation.management.system.configuration.JpaConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = JpaConfig.class)
@Slf4j
class HotelReservationManagementSystemTest {

    @Test
    void loadContext() {
        log.info("Context Loaded");
    }
}
