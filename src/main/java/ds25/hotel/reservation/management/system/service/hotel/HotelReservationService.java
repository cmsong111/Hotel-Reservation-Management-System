package ds25.hotel.reservation.management.system.service.hotel;

import ds25.hotel.reservation.management.system.dto.hotel.HotelReservationDto;
import ds25.hotel.reservation.management.system.entity.hotel.HotelReservation;
import ds25.hotel.reservation.management.system.entity.hotel.HotelRoom;
import ds25.hotel.reservation.management.system.repository.hotel.HotelReservationRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRoomRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRoomTypeRepository;
import ds25.hotel.reservation.management.system.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HotelReservationService {
    private final HotelRoomTypeRepository hotelRoomTypeRepository;
    private final HotelRoomRepository hotelRoomRepository;
    private final HotelReservationRepository hotelReservationRepository;
    private final UserRepository userRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public HotelReservationService(HotelReservationRepository hotelReservationRepository, UserRepository userRepository, HotelRoomRepository hotelRoomRepository,
                                   HotelRoomTypeRepository hotelRoomTypeRepository) {
        this.hotelReservationRepository = hotelReservationRepository;
        this.userRepository = userRepository;
        this.hotelRoomRepository = hotelRoomRepository;
        this.hotelRoomTypeRepository = hotelRoomTypeRepository;
    }

    /**
     * 호텔 예약 추가
     *
     * @param hotelReservationDto 호텔 예약 정보
     * @return 호텔 예약 정보
     * @throws IllegalArgumentException 잘못된 인자 값 전달
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
        hotelReservation.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        hotelReservation.setCreatedAt(new Timestamp(System.currentTimeMillis()));

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
    public List<HotelReservation> getHotelReservationByHotelRoomIdx(Long hotelRoomIdx) {
        log.info("호텔 객실 별 예약 내역을 조회합니다");
        return hotelReservationRepository.findByHotelRoom_Idx(hotelRoomIdx);
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
    public List<HotelReservation> getHotelReservationByHotelId(long hotelId) {
        log.info("호텔 별 호텔 예약 내역을 조회합니다");
        return hotelReservationRepository.findByHotelRoom_Idx(hotelId);
    }


    public void reservationPay(Long reservationIdx, int payment) {
        HotelReservation hotelReservation = hotelReservationRepository.findById(reservationIdx).orElseThrow();

        hotelReservation.setPayedMoney(hotelReservation.getPayedMoney() + payment);

        hotelReservationRepository.save(hotelReservation);
        log.info("Saved hotelReservation = {}", hotelReservation);
    }


    public Long getAvailableRoom(Long HotelRoomTypeIdx, Timestamp newCheckIn, Timestamp newCheckOut) {

        Long hotelRoomCount = hotelRoomRepository.countByRoomType_Idx(HotelRoomTypeIdx);


        Long reservationCount = hotelReservationRepository.countByCheckInDateBetweenAndCheckOutDateBetweenAndHotelRoom_RoomType_Idx(newCheckIn, newCheckOut, newCheckIn, newCheckOut, HotelRoomTypeIdx);
        log.info("hotelRoomCount = {}\treservationCount = {}", hotelRoomCount, reservationCount);

        return hotelRoomCount - reservationCount;
    }

}

