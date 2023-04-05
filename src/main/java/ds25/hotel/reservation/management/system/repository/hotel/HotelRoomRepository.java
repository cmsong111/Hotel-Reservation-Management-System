package ds25.hotel.reservation.management.system.repository.hotel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ds25.hotel.reservation.management.system.entity.hotel.Hotel;
import ds25.hotel.reservation.management.system.entity.hotel.HotelRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface HotelRoomRepository extends JpaRepository<HotelRoom, Long> {

    List<HotelRoom> findAll();

    List<HotelRoom> findByHotel_Idx(Long idx);

    Optional<HotelRoom> findById(Long idx);
}

