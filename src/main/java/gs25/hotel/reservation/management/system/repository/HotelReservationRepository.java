package gs25.hotel.reservation.management.system.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gs25.hotel.reservation.management.system.entity.HotelReservation;
import gs25.hotel.reservation.management.system.entity.HotelRoom;
import gs25.hotel.reservation.management.system.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;

@Slf4j
public class HotelReservationRepository {

    Gson gson = new Gson();
    ArrayList<HotelReservation> hotelReservations = new ArrayList<>();
    int idx = 0;


    public HotelReservationRepository() {
        loadFromJson();
    }

    public void loadFromJson() {
        Reader reader = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("db/hotelReservation.json"), StandardCharsets.UTF_8);
        hotelReservations = gson.fromJson(reader, new TypeToken<ArrayList<User>>() {
        }.getType());
        idx = hotelReservations.get(hotelReservations.size() - 1).getIdx();
        log.info("유저 데이터가 \"db/user.json\"에서 불러와졌습니다");
    }

    public void saveToJson() throws IOException {
        FileWriter file = new FileWriter(getClass().getClassLoader().getResource("db/hotelReservation.json").getPath());
        hotelReservations.sort(Comparator.comparing(HotelReservation::getIdx));
        gson.toJson(hotelReservations, file);
        file.flush();
        file.close();
        log.info("Hotel 데이터가 \"db/hotelReservation.json\"에 저장되었습니다");

    }



}
