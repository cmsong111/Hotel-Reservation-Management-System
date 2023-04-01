package gs25.hotel.reservation.management.system.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gs25.hotel.reservation.management.system.entity.Hotel;
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
public class HotelRoomRepository {

    Gson gson = new Gson();
    ArrayList<HotelRoom> hotelRooms = new ArrayList<>();
    int idx = 0;

    public HotelRoomRepository() {
        loadFromJson();
    }

    /**
     * Json에서 호텔 정보 불러오기
     *
     * @author 김남주
     */
    public void loadFromJson() {
        Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("db/hotelRoom.json"), StandardCharsets.UTF_8);
        hotelRooms = gson.fromJson(reader, new TypeToken<ArrayList<HotelRoom>>() {
        }.getType());
        idx = hotelRooms.get(hotelRooms.size() - 1).getIdx();
        log.info("호텔 데이터가 \"db/hotelRoom.json\"에서 불러와졌습니다");
    }

    /**
     * 호텔 정보를 Json으로 저장하는 메소드
     *
     * @throws IOException 파일 저장 실패 시
     * @author 김남주
     */
    public void saveToJson() throws IOException {
        FileWriter file = new FileWriter(getClass().getClassLoader().getResource("db/hotelRoom.json").getPath());
        hotelRooms.sort(Comparator.comparing(HotelRoom::getIdx));
        gson.toJson(hotelRooms, file);
        file.flush();
        file.close();
        log.info("Hotel 데이터가 \"db/hotelRoom.json\"에 저장되었습니다");
    }

    /**
     * 호텔 정보를 추가하는 메소드
     *
     * @param hotelRoom 추가할 호텔 정보
     * @throws IOException 파일 저장 실패 시
     * @author 김남주
     */
    public HotelRoom save(HotelRoom hotelRoom) throws IOException {
        if (hotelRoom.getIdx() == 0) {
            log.info("호텔 방 정보를 추가합니다");
            hotelRoom.setIdx(++idx);
            hotelRooms.add(hotelRoom);
        } else {
            log.info("호텔 방 정보를 수정합니다");
            for (int i = 0; i < hotelRooms.size(); i++) {
                if (hotelRooms.get(i).getIdx() == hotelRoom.getIdx()) {
                    hotelRooms.set(i, hotelRoom);
                    break;
                }
            }
        }
        saveToJson();
        return hotelRoom;
    }

    /**
     * 호텔 방 정보를 모두 가져오는 메소드
     *
     * @return 호텔 방 정보 리스트
     * @author 김남주
     */
    public ArrayList<HotelRoom> findAll() {
        log.info("모든 호텔 방 정보를 가져옵니다");
        return hotelRooms;
    }

    /**
     * 호텔 방 정보를 호텔 idx로 가져오는 메소드
     *
     * @param hotelIdx 호텔 idx
     * @return 호텔 방 정보 리스트
     * @author 김남주
     */
    public ArrayList<HotelRoom> findByHotelInx(int hotelIdx) {
        log.info(hotelIdx + "번 호텔 방 정보를 가져옵니다");
        ArrayList<HotelRoom> hotelRoomList = new ArrayList<>();
        for (HotelRoom hotelRoom : hotelRooms) {
            if (hotelRoom.getHotelIdx() == hotelIdx) {
                hotelRoomList.add(hotelRoom);
            }
        }
        return hotelRoomList;
    }

    /**
     * 호텔 방 정보를 idx로 삭제하는
     *
     * @param idx 호텔 방 idx
     * @author 김남주
     */
    public void delete(int idx) throws IOException {
        for (int i = 0; i < hotelRooms.size(); i++) {
            if (hotelRooms.get(i).getIdx() == idx) {
                hotelRooms.remove(i);
                log.info("호텔 방 정보가 삭제되었습니다");
                break;
            }
        }
        saveToJson();
    }
}

