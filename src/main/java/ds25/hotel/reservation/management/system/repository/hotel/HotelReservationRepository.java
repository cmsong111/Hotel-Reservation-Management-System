package ds25.hotel.reservation.management.system.repository.hotel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ds25.hotel.reservation.management.system.entity.hotel.HotelReservation;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

@Slf4j
public class HotelReservationRepository {

    Gson gson = new Gson();
    ArrayList<HotelReservation> hotelReservations = new ArrayList<>();
    int idx = 1;


    public HotelReservationRepository() {
        loadFromJson();
    }

    /**
     * json 파일에서 유저 데이터를 불러오는 메소드
     *
     * @throws IOException 파일 불러오기 실패
     * @author 김남주
     */
    public void loadFromJson() {
        Reader reader = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("db/hotelReservation.json"), StandardCharsets.UTF_8);
        hotelReservations = gson.fromJson(reader, new TypeToken<ArrayList<HotelReservation>>() {
        }.getType());
        if (hotelReservations.size() != 0) {
            idx = hotelReservations.get(hotelReservations.size() - 1).getIdx();
        }
        log.info("유저 데이터가 \"db/hotelReservation.json\"에서 불러와졌습니다");
    }

    /**
     * json 파일에 유저 데이터를 저장하는 메소드
     *
     * @throws IOException 파일 저장 실패
     * @author 김남주
     */
    public void saveToJson() throws IOException {
        FileWriter file = new FileWriter(getClass().getClassLoader().getResource("db/hotelReservation.json").getPath());
        hotelReservations.sort(Comparator.comparing(HotelReservation::getIdx));
        gson.toJson(hotelReservations, file);
        file.flush();
        file.close();
        log.info("Hotel 데이터가 \"db/hotelReservation.json\"에 저장되었습니다");
    }

    /**
     * 유저 정보를 저장하는 메소드
     *
     * @param hotelReservation 저장할 유저 정보
     * @return 저장된 유저 정보
     * @throws IOException 파일 저장 실패
     * @author 김남주
     */
    public HotelReservation save(HotelReservation hotelReservation) throws IOException {
        if (hotelReservation.getIdx() == 0) {
            hotelReservation.setIdx(++idx);
            hotelReservations.add(hotelReservation);
            log.info("유저 데이터가 저장되었습니다");
        } else {
            for (int i = 0; i < hotelReservations.size(); i++) {
                if (hotelReservations.get(i).getIdx() == hotelReservation.getIdx()) {
                    hotelReservations.set(i, hotelReservation);
                    log.info("유저 데이터가 저장되었습니다");
                    break;
                }
            }
        }
        saveToJson();
        return hotelReservation;
    }

    /**
     * 유저가 예약한 호텔 정보를 조회하는 메소드
     *
     * @param idx 조회할 유저의 인덱스
     * @return 유저의 예약 정보
     * @throws IOException 파일 저장 실패
     * @author 김남주
     */
    public Optional<HotelReservation> findById(int idx) {
        for (HotelReservation hotelReservation : hotelReservations) {
            if (hotelReservation.getIdx() == idx) {
                log.info("유저 데이터가 조회되었습니다");
                return Optional.of(hotelReservation);
            }
        }
        log.info("유저 데이터가 조회되지 않았습니다");
        return Optional.empty();
    }

    /**
     * 모든 예약 정보를 조회하는 메소드
     *
     * @return 모든 예약 정보
     * @throws IOException 파일 저장 실패
     * @author 김남주
     */
    public ArrayList<HotelReservation> findAll() {
        log.info("모든 유저 데이터가 조회되었습니다");
        return hotelReservations;
    }

    /**
     * 유저의 예약 내역을 조회하는 메소드
     *
     * @param userIdx 조회할 유저의 인덱스
     * @return 유저의 예약 내역
     * @throws IOException 파일 저장 실패
     * @author 김남주
     */
    public ArrayList<HotelReservation> findByUserId(int userIdx) {
        ArrayList<HotelReservation> hotelReservations = new ArrayList<>();
        for (HotelReservation hotelReservation : hotelReservations) {
            if (hotelReservation.getUserIdx() == userIdx) {
                hotelReservations.add(hotelReservation);
            }
        }
        log.info("유저의 예약 내역이 조회되었습니다");
        return hotelReservations;
    }

    /**
     * 호텔의 예약 내역을 조회하는 메소드
     *
     * @param hotelRoomIdx 조회할 호텔의 인덱스
     * @return 호텔의 예약 내역
     * @throws IOException 파일 저장 실패
     * @author 김남주
     */
    public ArrayList<HotelReservation> findByHotelRoomId(int hotelRoomIdx) {
        ArrayList<HotelReservation> hotelReservations = new ArrayList<>();
        for (HotelReservation hotelReservation : hotelReservations) {
            if (hotelReservation.getHotelRoomIdx() == hotelRoomIdx) {
                hotelReservations.add(hotelReservation);
            }
        }
        log.info(hotelRoomIdx + "번 방 예약 내역이 조회되었습니다");
        return hotelReservations;
    }

    /**
     * 호텔의 예약 내역을 조회하는 메소드
     *
     * @param hotelIdx 조회할 호텔의 인덱스
     * @return 호텔의 예약 내역
     * @throws IOException 파일 저장 실패
     * @author 김남주
     */
    public ArrayList<HotelReservation> findByHotelId(int hotelIdx) {
        ArrayList<HotelReservation> hotelReservations = new ArrayList<>();
        for (HotelReservation hotelReservation : hotelReservations) {
            if (hotelReservation.getHotelIdx() == hotelIdx) {
                hotelReservations.add(hotelReservation);
            }
        }
        log.info(hotelIdx + "번 호텔의 예약 내역이 조회되었습니다");
        return hotelReservations;
    }

    /**
     * 예약 정보를 삭제하는 메소드
     *
     * @param idx 삭제할 예약 정보의 인덱스
     * @throws IOException 파일 저장 실패
     * @author 김남주
     */
    public void deleteById(int idx) throws IOException {
        for (int i = 0; i < hotelReservations.size(); i++) {
            if (hotelReservations.get(i).getIdx() == idx) {
                hotelReservations.remove(i);
                break;
            }
        }
        log.info(idx + "번 예약 정보가 삭제되었습니다");
        saveToJson();
    }


}
