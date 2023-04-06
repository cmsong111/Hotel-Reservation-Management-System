package ds25.hotel.reservation.management.system.service.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.Hotel;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HotelService {
    HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }


    /**
     * 호텔 추가
     *
     * @param hotel 호텔 정보
     * @return 호텔 정보
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public Hotel addHotel(Hotel hotel) {
        if (hotel.getIdx() == 0) {
            log.info("호텔 정보가 추가되었습니다");
            return hotelRepository.save(hotel);
        } else {
            log.info("호텔 정보가 추가되지 않았습니다");
            return null;
        }
    }

    /**
     * 호텔 삭제
     *
     * @param hotelIdx 호텔 index 번호
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public void removeHotel(Hotel hotel) {
        Optional<Hotel> oldHotel = hotelRepository.findById(hotel.getIdx());
        if (oldHotel.isPresent()) {
            log.info("호텔 정보가 삭제되었습니다");
            hotelRepository.delete(oldHotel.get());
        } else {
            log.info("삭제할 호텔 정보가 없습니다");
        }
    }

    /**
     * 호텔 정보 수정
     *
     * @param hotel 호텔 정보
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public Hotel updateHotel(Hotel hotel) {
        Optional<Hotel> oldHotel = hotelRepository.findById(hotel.getIdx());
        if (oldHotel.isPresent()) {
            log.info("호텔 정보가 수정되었습니다");
            return hotelRepository.save(hotel);
        } else {
            log.info("수정할 호텔 정보가 없습니다");
            return null;
        }
    }

    /**
     * 호텔 번호로 정보 조회
     *
     * @param idx 호텔 번호
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public Optional<Hotel> getHotel(long idx) {
        return hotelRepository.findById(idx);
    }

    /**
     * 호텔 전체 목록 조회
     *
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public List<Hotel> getHotelList() {
        return hotelRepository.findAll();
    }

    /**
     * 호텔 이름으로 정보 조회
     *
     * @param name 호텔 이름
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public List<Hotel> getHotelByName(String name) throws IOException {
        log.info("호텔 이름으로 정보 조회 : " + name);
        return hotelRepository.findByName(name);
    }
}
