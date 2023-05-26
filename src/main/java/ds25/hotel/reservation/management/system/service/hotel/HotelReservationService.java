package ds25.hotel.reservation.management.system.service.hotel;

import ds25.hotel.reservation.management.system.dto.hotel.HotelReservationDto;
import ds25.hotel.reservation.management.system.entity.hotel.HotelReservation;
import ds25.hotel.reservation.management.system.entity.hotel.HotelRoom;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelReservationRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRoomRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRoomTypeRepository;
import ds25.hotel.reservation.management.system.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    private final HotelRepository hotelRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public HotelReservationService(HotelReservationRepository hotelReservationRepository, UserRepository userRepository, HotelRoomRepository hotelRoomRepository,
                                   HotelRoomTypeRepository hotelRoomTypeRepository,HotelRepository hotelRepository) {
        this.hotelReservationRepository = hotelReservationRepository;
        this.userRepository = userRepository;
        this.hotelRoomRepository = hotelRoomRepository;
        this.hotelRoomTypeRepository = hotelRoomTypeRepository;
        this.hotelRepository = hotelRepository;
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
    public List<HotelReservationDto> getHotelReservationByHotelId(long hotelId) {
        log.info("호텔 별 호텔 예약 내역을 조회합니다");
        List<HotelReservationDto> hotelReservationDtos = new ArrayList<>();

        hotelReservationRepository.findByHotelRoom_RoomType_Hotel_Idx(hotelId).forEach(
                hotelReservation -> hotelReservationDtos.add(modelMapper.map(hotelReservation, HotelReservationDto.class))
        );

        return hotelReservationDtos;
    }


    public void reservationPay(Long reservationIdx, int payment) {
        HotelReservation hotelReservation = hotelReservationRepository.findById(reservationIdx).orElseThrow();

        hotelReservation.setPayedMoney(hotelReservation.getPayedMoney() + payment);

        hotelReservationRepository.save(hotelReservation);
        log.info("Saved hotelReservation = {}", hotelReservation);
    }

    /**
     * 호텔 예약 가능한 방 개수 조회
     *
     * @param HotelRoomTypeIdx 조회 하려는 호텔 방 타입
     * @param newCheckIn       새로 예약하려는 체크인 날짜
     * @param newCheckOut      새로 예약하려는 체크아웃 날짜
     * @return 예약 가능한 방 개수
     * @author 김남주
     */
    public Long getAvailableRoom(Long HotelRoomTypeIdx, Timestamp newCheckIn, Timestamp newCheckOut) {

        Long hotelRoomCount = hotelRoomRepository.countByRoomType_Idx(HotelRoomTypeIdx);


        Long reservationCount = hotelReservationRepository.countByCheckInDateBetweenAndCheckOutDateBetweenAndHotelRoom_RoomType_Idx(newCheckIn, newCheckOut, newCheckIn, newCheckOut, HotelRoomTypeIdx);
        log.info("hotelRoomCount = {}\treservationCount = {}", hotelRoomCount, reservationCount);

        return hotelRoomCount - reservationCount;
    }

    /**
     * 호텔 예약 가능 여부 확인
     *
     * @param HotelRoomIdx 호텔 방 index 번호
     * @param newCheckIn   새로 예약하려는 체크인 날짜
     * @param newCheckOut  새로 예약하려는 체크아웃 날짜
     * @return 예약 가능 여부 (true : 가능, false : 불가능)
     * @author 김남주
     */
    public boolean isAvailableRoom(Long HotelRoomIdx, Timestamp newCheckIn, Timestamp newCheckOut) {
        return !hotelReservationRepository.existsByHotelRoomType_IdxAndCheckInDateBetweenAndCheckOutDateBetween(HotelRoomIdx, newCheckIn, newCheckOut, newCheckIn, newCheckOut);
    }

    public HotelReservationDto createReservationID(HotelReservationDto hotelReservationDto){
        HotelReservation hotelReservation = modelMapper.map(hotelReservationDto, HotelReservation.class);
        // 방 설정
        List<HotelRoom> hotelRooms = hotelRoomRepository.findByRoomType_Idx(hotelReservationDto.getHotelRoomTypeIdx());
        for (HotelRoom hotelRoom : hotelRooms) {
            if (isAvailableRoom(hotelRoom.getIdx(), hotelReservationDto.getCheckInDate(), hotelReservationDto.getCheckOutDate())) {
                hotelReservationDto.setHotelRoomIdx(hotelRoom.getIdx());
                break;
            }
        }
        if (hotelReservationDto.getHotelRoomIdx() == 0L) {
            throw new IllegalArgumentException("예약 가능한 방이 없습니다");
        }

        return modelMapper.map(hotelReservationRepository.save(hotelReservation), HotelReservationDto.class);
    }

    public HotelReservationDto createHotelReservation(HotelReservationDto hotelReservationDto) {
        if (hotelReservationDto.getCheckInDate().after(hotelReservationDto.getCheckOutDate())) {
            throw new IllegalArgumentException("체크인 날짜가 체크아웃 날짜보다 늦습니다");
        }
        if (hotelReservationDto.getCheckInDate().before(new Timestamp(System.currentTimeMillis()))) {
            throw new IllegalArgumentException("체크인 날짜가 현재 날짜보다 빠릅니다");
        }
        if (hotelReservationDto.getCheckOutDate().before(new Timestamp(System.currentTimeMillis()))) {
            throw new IllegalArgumentException("체크아웃 날짜가 현재 날짜보다 빠릅니다");
        }
        if (hotelReservationDto.getCheckInDate().equals(hotelReservationDto.getCheckOutDate())) {
            throw new IllegalArgumentException("체크인 날짜와 체크아웃 날짜가 같습니다");
        }
        if (hotelReservationDto.getCheckInDate().equals(hotelReservationDto.getCheckOutDate())) {
            throw new IllegalArgumentException("체크인 날짜와 체크아웃 날짜가 같습니다");
        }
        // 예약 가능한 방 개수 조회
        Long availableRoom = getAvailableRoom(hotelReservationDto.getHotelRoomTypeIdx(), hotelReservationDto.getCheckInDate(), hotelReservationDto.getCheckOutDate());
        if (availableRoom <= 0) {
            throw new IllegalArgumentException("예약 가능한 방이 없습니다");
        }

        // 방 설정
        List<HotelRoom> hotelRooms = hotelRoomRepository.findByRoomType_Idx(hotelReservationDto.getHotelRoomTypeIdx());
        for (HotelRoom hotelRoom : hotelRooms) {
            if (isAvailableRoom(hotelRoom.getIdx(), hotelReservationDto.getCheckInDate(), hotelReservationDto.getCheckOutDate())) {
                hotelReservationDto.setHotelRoomIdx(hotelRoom.getIdx());
                break;
            }
        }
        if (hotelReservationDto.getHotelRoomIdx() == 0L) {
            throw new IllegalArgumentException("예약 가능한 방이 없습니다");
        }


        HotelReservation hotelReservation = modelMapper.map(hotelReservationDto, HotelReservation.class);
        hotelReservation.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        hotelReservation.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        hotelReservation.setUser(userRepository.findById(hotelReservationDto.getUserId()).orElseThrow());
        return modelMapper.map(hotelReservationRepository.save(hotelReservation), HotelReservationDto.class);
    }

}

