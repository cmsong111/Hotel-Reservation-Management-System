package gs25.hotel.reservation.management.system.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gs25.hotel.reservation.management.system.entity.Hotel;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;

@Slf4j
public class HotelRepository {
    Gson gson = new Gson();
    ArrayList<Hotel> hotels = new ArrayList<>();
    int idx = 0;

    public HotelRepository() {
        loadFromJson();
    }

    public void loadFromJson() {
        Reader reader = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("db/hotel.json"), StandardCharsets.UTF_8);
        hotels = gson.fromJson(reader, new TypeToken<ArrayList<Hotel>>() {
        }.getType());
        idx = hotels.get(hotels.size() - 1).getIdx();
        log.info("호텔 데이터가 \"db/hotel.json\"에서 불러와졌습니다");
    }

    public void saveToJson() throws IOException {
        FileWriter file = new FileWriter(getClass().getClassLoader().getResource("db/hotel.json").getPath());
        hotels.sort(Comparator.comparing(Hotel::getIdx));
        gson.toJson(hotels, file);
        file.flush();
        file.close();
        log.info("Hotel 데이터가 \"db/hotel.json\"에 저장되었습니다");
    }
}
