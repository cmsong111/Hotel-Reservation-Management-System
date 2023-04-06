package ds25.hotel.reservation.management.system.service.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.HotelReservation;
import ds25.hotel.reservation.management.system.repository.hotel.HotelReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

@Slf4j
@Service
public class HotelReservationService {

    HotelReservationRepository hotelReservationRepository;

    @Autowired
    public HotelReservationService(HotelReservationRepository hotelReservationRepository) {
        this.hotelReservationRepository = hotelReservationRepository;
    }
    /**
     * 호텔 예약 추가
     *
     * @param hotelReservation 호텔 예약 정보
     * @return 호텔 예약 정보
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public HotelReservation addHotelReservation(HotelReservation hotelReservation) throws Exception {
        if (hotelReservation.getIdx() == 0) {
            log.info("호텔 예약이 추가되었습니다");
            return hotelReservationRepository.save(hotelReservation);
        } else {
            log.error("이미 존재하는 호텔 예약 정보입니다");
            throw new Exception("이미 존재하는 호텔 예약 정보입니다");
        }
    }

    /**
     * 호텔 예약 정보 수정
     *
     * @param hotelReservation 호텔 예약 정보
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public HotelReservation updateHotelReservation(HotelReservation hotelReservation) throws IOException {
        Optional<HotelReservation> oldHotelReservation = hotelReservationRepository.findById(hotelReservation.getIdx());
        if (oldHotelReservation.isPresent()) {
            log.info("호텔 예약이 수정되었습니다");
            return hotelReservationRepository.save(hotelReservation);
        } else {
            log.error("존재하지 않는 호텔 예약 정보입니다");
            return null;
        }
    }

    /**
     * 호텔 예약 삭제
     *
     * @param hotelReservationIdx 호텔 예약 index 번호
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public void removeHotelReservation(Long hotelReservationIdx)  {
        Optional<HotelReservation> oldHotelReservation = hotelReservationRepository.findById(hotelReservationIdx);
        if (oldHotelReservation.isPresent()) {
            log.info("호텔 예약이 삭제되었습니다");
            hotelReservationRepository.delete(oldHotelReservation.get());
        } else {
            log.error("존재하지 않는 호텔 예약 정보입니다");
        }
    }

    /**
     * 특정 호텔 예약 내역 조회
     *
     * @param hotelReservationIdx 호텔 예약 index 번호
     * @return 호텔 예약 정보
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public HotelReservation getHotelReservation(Long hotelReservationIdx) throws Exception {
        Optional<HotelReservation> oldHotelReservation = hotelReservationRepository.findById(hotelReservationIdx);
        if (oldHotelReservation.isPresent()) {
            log.info("호텔 예약 정보를 조회합니다");
            return oldHotelReservation.get();
        } else {
            log.error("존재하지 않는 호텔 예약 정보입니다");
            throw new Exception("존재하지 않는 호텔 예약 정보입니다");
        }
    }

    /**
     * 호텔 객실 별 예약 내역 조회
     *
     * @param hotelRoomIdx 호텔 방 index 번호
     * @return 호텔 예약 정보
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public List<HotelReservation> getHotelReservationByHotelRoomIdx(Long hotelRoomIdx) throws IOException {
        log.info("호텔 객실 별 예약 내역을 조회합니다");
        return hotelReservationRepository.findByHotel_Idx(hotelRoomIdx);
    }

    /**
     * 유저 별 호텔 예약 내역 조회
     *
     * @param userId 사용자 idx
     * @return 호텔 예약 정보
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public List<HotelReservation> findByUser_Id(String id)  {
        log.info("유저 별 호텔 예약 내역을 조회합니다");
        return hotelReservationRepository.findByUser_Id(id);
    }

    /**
     * 호텔 별 호텔 예약 내역 조회
     *
     * @param hotelId 호텔 idx
     * @return 호텔 예약 정보
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public List<HotelReservation> getHotelReservationByHotelId(long hotelId) throws IOException {
        log.info("호텔 별 호텔 예약 내역을 조회합니다");
        return hotelReservationRepository.findByHotel_Idx(hotelId);
    }

    /**
     * 예약 날짜 내에 사용중인 객실 수를 반환
     *
     * @param hotelRoomIdx 호텔 객실 idx
     * @param start        예약 시작 날짜
     * @param end          예약 종료 날짜
     * @return 사용중인 객실 수
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public int getUsingRoomCount(Long hotelRoomIdx, Timestamp start, Timestamp end) throws IOException {
        List<HotelReservation> hotelReservations = hotelReservationRepository.findByHotelRoom_IdxAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(hotelRoomIdx, start, end);

        for (HotelReservation hotelReservation : hotelReservations) {

            log.info(hotelReservation.toString());
        }

        int availableRoomCount = 0;

        if (hotelReservations.isEmpty()) {
            return 0;
        }

        hotelReservations.sort(Comparator.comparing(HotelReservation::getCheckOutDate));
        PriorityQueue<HotelReservation> priorityQueue = new PriorityQueue<>(Comparator.comparing(HotelReservation::getCheckOutDate));

        priorityQueue.add(hotelReservations.get(0));
        for (int i = 1; i < hotelReservations.size(); i++) {
            HotelReservation hotelReservation = hotelReservations.get(i);
            HotelReservation peek = priorityQueue.peek();
            if (hotelReservation.getCheckInDate().after(peek.getCheckOutDate())) {
                priorityQueue.poll();
            }
            priorityQueue.add(hotelReservation);
        }

        return priorityQueue.size();
    }

}
