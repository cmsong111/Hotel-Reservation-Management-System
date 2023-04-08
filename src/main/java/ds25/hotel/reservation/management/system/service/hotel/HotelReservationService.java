package ds25.hotel.reservation.management.system.service.hotel;

import ds25.hotel.reservation.management.system.dto.hotel.HotelReservationDto;
import ds25.hotel.reservation.management.system.entity.hotel.HotelReservation;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelReservationRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRoomRepository;
import ds25.hotel.reservation.management.system.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Slf4j
@Service
public class HotelReservationService {

    HotelReservationRepository hotelReservationRepository;
    HotelRepository hotelRepository;
    HotelRoomRepository hotelRoomRepository;
    UserRepository userRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public HotelReservationService(HotelReservationRepository hotelReservationRepository, HotelRepository hotelRepository, HotelRoomRepository hotelRoomRepository, UserRepository userRepository) {
        this.hotelReservationRepository = hotelReservationRepository;
        this.hotelRepository = hotelRepository;
        this.hotelRoomRepository = hotelRoomRepository;
        this.userRepository = userRepository;
    }

    /**
     * 호텔 예약 추가
     *
     * @param hotelReservationDto 호텔 예약 정보
     * @return 호텔 예약 정보
     * @throws Exception 예외 처리
     * @author 김남주
     */
    public HotelReservationDto addHotelReservation(HotelReservationDto hotelReservationDto) throws IllegalArgumentException {
        if (hotelReservationDto.getHotelIdx() == 0) {
            throw new IllegalArgumentException("호텔 정보가 없습니다");
        } else if (hotelReservationDto.getHotelRoomIdx() == 0) {
            throw new IllegalArgumentException("호텔 방 정보가 없습니다");
        } else if (hotelReservationDto.getCheckInDate() == null) {
            throw new IllegalArgumentException("체크인 날짜가 없습니다");
        } else if (hotelReservationDto.getCheckOutDate() == null) {
            throw new IllegalArgumentException("체크아웃 날짜가 없습니다");
        } else if (hotelReservationDto.getPeopleCount() == 0) {
            throw new IllegalArgumentException("인원 수가 없습니다");
        } else if (hotelReservationDto.getUserId() == null) {
            throw new IllegalArgumentException("유저 정보가 없습니다");
        }
        HotelReservation hotelReservation = modelMapper.map(hotelReservationDto, HotelReservation.class);
        hotelReservation.setUser(userRepository.findById(hotelReservationDto.getUserId()).get());
        hotelReservation.setHotelRoom(hotelRoomRepository.findById(hotelReservationDto.getHotelRoomIdx()).get());
        hotelReservation.setCheckInDate(hotelReservationDto.getCheckInDate());
        hotelReservation.setCheckOutDate(hotelReservationDto.getCheckOutDate());

        return modelMapper.map(hotelReservationRepository.save(hotelReservation), HotelReservationDto.class);
    }

    /**
     * 호텔 예약 정보 수정
     *
     * @param hotelReservationDto 호텔 예약 정보
     * @throws IllegalArgumentException 잘못된 인자 예외
     * @author 김남주
     */
    public HotelReservationDto modifyHotelReservation(HotelReservationDto hotelReservationDto) throws IllegalArgumentException {
        Optional<HotelReservation> oldHotelReservation = hotelReservationRepository.findById(hotelReservationDto.getIdx());

        if (oldHotelReservation.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 호텔 예약 정보입니다");
        }

        oldHotelReservation.get().setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return modelMapper.map(hotelReservationRepository.save(oldHotelReservation.get()), HotelReservationDto.class);

    }

    /**
     * 호텔 예약 삭제
     *
     * @param hotelReservationIdx 호텔 예약 index 번호
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public void removeHotelReservation(Long hotelReservationIdx) {
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
    public HotelReservationDto findHotelReservation(Long hotelReservationIdx) throws Exception {
        log.info("호텔 예약 정보를 조회합니다, hotelReservationIdx = {}", hotelReservationIdx);
        Optional<HotelReservation> oldHotelReservation = hotelReservationRepository.findById(hotelReservationIdx);
        if (oldHotelReservation.isPresent()) {
            return modelMapper.map(oldHotelReservation.get(), HotelReservationDto.class);
        } else {
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
        return hotelReservationRepository.findByHotelRoom_Hotel_Idx(hotelRoomIdx);
    }

    /**
     * 유저 별 호텔 예약 내역 조회
     *
     * @param id 사용자 id
     * @return 호텔 예약 정보
     * @author 김남주
     */
    public List<HotelReservationDto> findHotelReservationByUserId(String id) {
        log.info("유저 별 호텔 예약 내역을 조회합니다");
        List<HotelReservation> hotelReservations = hotelReservationRepository.findByUser_Id(id);
        List<HotelReservationDto> hotelReservationDtos = new ArrayList<>();
        for (HotelReservation hotelReservation : hotelReservations) {
            HotelReservationDto hotelReservationDto = modelMapper.map(hotelReservation, HotelReservationDto.class);
            hotelReservationDto.setHotelRoomIdx(hotelReservation.getHotelRoom().getIdx());
            hotelReservationDtos.add(hotelReservationDto);
        }
        return hotelReservationDtos;
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
        return hotelReservationRepository.findByHotelRoom_Hotel_Idx(hotelId);
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

    public void initHotelReservationData(List<HotelReservation> hotelReservations) {
        log.info("호텔 예약 데이터를 초기화합니다");

        hotelReservationRepository.saveAll(hotelReservations);

        for (HotelReservation hotelReservation : hotelReservationRepository.findAll()) {
            log.info("Saved hotelReservation = {}", hotelReservation);
        }
    }

}
