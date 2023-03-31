package gs25.hotel.reservation.management.system.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gs25.hotel.reservation.management.system.entity.Hotel;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

@Slf4j
public class HotelRepository {
    Gson gson = new Gson();
    ArrayList<Hotel> hotels = new ArrayList<>();
    int idx = 0;

    public HotelRepository() {
        loadFromJson();
    }

    /**
     * Json에서 호텔 정보 불러오기
     *
     * @author 김남주
     */
    public void loadFromJson() {
        Reader reader = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("db/hotel.json"), StandardCharsets.UTF_8);
        hotels = gson.fromJson(reader, new TypeToken<ArrayList<Hotel>>() {
        }.getType());
        idx = hotels.get(hotels.size() - 1).getIdx();
        log.info("호텔 데이터가 \"db/hotel.json\"에서 불러와졌습니다");
    }

    /**
     * 호텔 정보 Json에 저장하기
     *
     * @throws IOException 파일 저장 실패
     * @author 김남주
     */
    public void saveToJson() throws IOException {
        FileWriter file = new FileWriter(getClass().getClassLoader().getResource("db/hotel.json").getPath());
        hotels.sort(Comparator.comparing(Hotel::getIdx));
        gson.toJson(hotels, file);
        file.flush();
        file.close();
        log.info("Hotel 데이터가 \"db/hotel.json\"에 저장되었습니다");
    }

    /**
     * 호텔 정보 전부 가져오기
     *
     * @return 호텔 정보 리스트
     * @author 김남주
     */
    public ArrayList<Hotel> findAll() {
        return hotels;
    }

    /**
     * 호텔 정보 하나 가져오기
     *
     * @param idx 호텔 인덱스
     * @return 호텔 정보
     * @author 김남주
     */
    public Optional<Hotel> findByIdx(int idx) {
        for (Hotel hotel : hotels) {
            if (hotel.getIdx() == idx) {
                return Optional.of(hotel);
            }
        }
        return Optional.empty();
    }

    /**
     * 호텔 이름으로 검색
     *
     * @param name 호텔 이름
     * @return 호텔 정보 리스트
     * @author 김남주
     */
    public ArrayList<Hotel> findByName(String name) {
        ArrayList<Hotel> result = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (hotel.getName().contains(name)) {
                result.add(hotel);
            }
        }
        return result;
    }

    /**
     * 호텔 정보 저장 및 수정 메소드
     *
     * @param hotel 저장할 호텔 정보
     * @return 저장된 호텔 정보
     * @throws IOException 호텔 정보 저장 실패
     * @author 김남주
     */
    public Hotel save(Hotel hotel) throws IOException {
        if (hotel.getIdx() == 0) {
            hotel.setIdx(++idx);
            hotels.add(hotel);
        } else {
            for (int i = 0; i < hotels.size(); i++) {
                if (hotels.get(i).getIdx() == hotel.getIdx()) {
                    hotels.set(i, hotel);
                    break;
                }
            }
        }
        saveToJson();
        return hotel;
    }

    /**
     * 호텔 정보 삭제 메소드
     *
     * @param idx 삭제할 호텔 인덱스
     * @throws IOException 호텔 정보 저장 실패
     * @author 김남주
     */
    public void deleteByIdx(int idx) throws IOException {
        for (int i = 0; i < hotels.size(); i++) {
            if (hotels.get(i).getIdx() == idx) {
                hotels.remove(i);
                break;
            }
        }
        saveToJson();
    }
}
