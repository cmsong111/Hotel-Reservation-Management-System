package ds25.hotel.reservation.management.system.service.hotel;

import ds25.hotel.reservation.management.system.configuration.Singleton;

import ds25.hotel.reservation.management.system.entity.hotel.HotelRoom;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRoomRepository;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public class HotelRoomService {
    HotelRoomRepository hotelRoomRepository = Singleton.getInstance().getHotelRoomRepository();

    /**
     * 호텔 객실 추가
     *
     * @param hotelRoom 호텔 객실 정보
     * @return 호텔 객실 정보
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public HotelRoom addHotelRoom(HotelRoom hotelRoom) throws IOException {
        if (hotelRoom.getIdx() == 0) {
            log.info("호텔 정보 객실이 추가되었습니다");
            return hotelRoomRepository.save(hotelRoom);
        } else {
            log.error("이미 존재하는 호텔 객실 정보입니다");
            return null;
        }
    }

    /**
     * 호텔 객실 정보 수정
     *
     * @param hotelRoom 호텔 객실 정보
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public HotelRoom updateHotelRoom(HotelRoom hotelRoom) throws IOException {
        Optional<HotelRoom> oldHotelRoom = hotelRoomRepository.findByIdx(hotelRoom.getIdx());
        if (oldHotelRoom.isPresent()) {
            log.info("호텔 정보 객실이 수정되었습니다");
            return hotelRoomRepository.save(hotelRoom);
        } else {
            log.error("존재하지 않는 호텔 객실 정보입니다");
            return null;
        }
    }

    /**
     * 호텔 객실 삭제
     *
     * @param hotelRoomIdx 호텔 객실 index 번호
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public void removeHotelRoom(int hotelRoomIdx) throws IOException {
        Optional<HotelRoom> oldHotelRoom = hotelRoomRepository.findByIdx(hotelRoomIdx);
        if (oldHotelRoom.isPresent()) {
            hotelRoomRepository.deleteByIdx(hotelRoomIdx);
            log.info("호텔 객실 정보가 삭제되었습니다");
        } else {
            log.error("존재하지 않는 호텔 객실 정보입니다");
        }
    }

    /**
     * 호텔 객실 정보 조회
     *
     * @param hotelRoomIdx 호텔 객실 index 번호
     * @return 호텔 객실 정보
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public HotelRoom getHotelRoom(int hotelRoomIdx) throws IOException {
        Optional<HotelRoom> oldHotelRoom = hotelRoomRepository.findByIdx(hotelRoomIdx);
        if (oldHotelRoom.isPresent()) {
            log.info("호텔 객실 정보가 조회되었습니다");
            return oldHotelRoom.get();
        } else {
            log.error("존재하지 않는 호텔 객실 정보입니다");
            return null;
        }
    }
}
