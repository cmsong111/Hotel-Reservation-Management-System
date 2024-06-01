package ds25.hotel.reservation.management.system.domain.hotel.application;

import ds25.hotel.reservation.management.system.di.Service;
import ds25.hotel.reservation.management.system.domain.hotel.HotelRoom;
import ds25.hotel.reservation.management.system.domain.hotel.HotelRoomRepository;
import ds25.hotel.reservation.management.system.domain.hotel.HotelRoomTypeRepository;
import ds25.hotel.reservation.management.system.ui.dto.hotel.HotelRoomDto;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HotelRoomService {
    private final HotelRoomTypeRepository hotelRoomTypeRepository;
    private final HotelRoomRepository hotelRoomRepository;

    @Inject
    public HotelRoomService(HotelRoomRepository hotelRoomRepository, HotelRoomTypeRepository hotelRoomTypeRepository) {
        this.hotelRoomRepository = hotelRoomRepository;
        this.hotelRoomTypeRepository = hotelRoomTypeRepository;
    }


    /**
     * 호텔 방 정보 추가
     *
     * @param hotelRoomDto 호텔 방 정보
     * @return 호텔 방 정보 DTO
     * @author 김남주
     */
    public HotelRoomDto addHotelRoom(HotelRoomDto hotelRoomDto) {
        HotelRoom hotelRoom = hotelRoomRepository.save(modelMapper.map(hotelRoomDto, HotelRoom.class));
        log.info("addHotelRoom: " + hotelRoom.toString());
        return modelMapper.map(hotelRoom, HotelRoomDto.class);
    }

    /**
     * 호텔 방 정보 삭제
     *
     * @param hotelROomIdx 호텔 방 정보 인덱스
     * @author 김남주
     */
    public void deleteHotelRoom(Long hotelROomIdx) {
        Optional<HotelRoom> hotelRoom = hotelRoomRepository.findById(hotelROomIdx);

        if (hotelRoom.isPresent()) {
            log.info("deleteHotelRoom: " + hotelROomIdx + " deleted");
            hotelRoomRepository.delete(hotelRoom.get());
        } else {
            log.info("deleteHotelRoom: " + hotelROomIdx + " not found");
        }
    }

    /**
     * 호텔 아이디로 호텔 방 정보 가져오기
     *
     * @param hotelId 호텔 아이디
     * @return 호텔 방 정보 DTO 리스트
     * @author 김남주
     */
    public List<HotelRoomDto> findHotelRoomsByHotelId(Long hotelId) {
        List<HotelRoom> hotelRooms = hotelRoomRepository.findByRoomType_Hotel_Idx(hotelId);
        List<HotelRoomDto> hotelRoomDtos = new ArrayList<>();
        for (HotelRoom hotelRoom : hotelRooms) {
            hotelRoomDtos.add(modelMapper.map(hotelRoom, HotelRoomDto.class));
        }
        return hotelRoomDtos;
    }

    /**
     * 호텔 방 정보 수정
     *
     * @param hotelRoomDto 호텔 방 정보
     * @return 호텔 방 정보 DTO
     * @author 김남주
     */
    public HotelRoomDto updateHotelRoom(HotelRoomDto hotelRoomDto) {
        HotelRoom hotelRoom = hotelRoomRepository.findById(hotelRoomDto.getIdx()).orElseThrow();

        hotelRoom.setRoomNumber(hotelRoomDto.getRoomNumber());
        hotelRoom.setRoomType(hotelRoomTypeRepository.findById(hotelRoomDto.getRoomTypeIdx()).get());

        return modelMapper.map(hotelRoom, HotelRoomDto.class);
    }

    /**
     * 호텔 방 타입 Idx 로 호텔 방 정보 가져오기
     *
     * @param hotelTypeIdx 호텔 방 타입 Idx
     * @return 호텔 방 정보 DTO 리스트
     * @author 김남주
     */
    public List<HotelRoomDto> findByHotelRoomTypeIdx(Long hotelTypeIdx) {
        List<HotelRoom> hotelRooms = hotelRoomRepository.findByRoomType_Idx(hotelTypeIdx);
        List<HotelRoomDto> hotelRoomDtos = new ArrayList<>();
        for (HotelRoom hotelRoom : hotelRooms) {
            hotelRoomDtos.add(modelMapper.map(hotelRoom, HotelRoomDto.class));
        }
        return hotelRoomDtos;
    }
}
