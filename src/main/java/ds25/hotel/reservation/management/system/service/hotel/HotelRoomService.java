package ds25.hotel.reservation.management.system.service.hotel;

import com.google.gson.Gson;
import ds25.hotel.reservation.management.system.dto.hotel.HotelRoomDto;
import ds25.hotel.reservation.management.system.entity.hotel.HotelRoom;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRoomRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRoomTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HotelRoomService {
    private final HotelRoomTypeRepository hotelRoomTypeRepository;

    HotelRoomRepository hotelRoomRepository;
    Gson gson = new Gson();
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public HotelRoomService(HotelRoomRepository hotelRoomRepository,
                            HotelRoomTypeRepository hotelRoomTypeRepository) {
        this.hotelRoomRepository = hotelRoomRepository;
        this.hotelRoomTypeRepository = hotelRoomTypeRepository;
    }


    public HotelRoomDto addHotelRoom(HotelRoomDto hotelRoomDto) {
        HotelRoom hotelRoom = hotelRoomRepository.save(modelMapper.map(hotelRoomDto, HotelRoom.class));
        log.info("addHotelRoom: " + gson.toJson(hotelRoom));
        return modelMapper.map(hotelRoom, HotelRoomDto.class);
    }


    public void deleteHotelRoom(Long hotelRoomId) {
        Optional<HotelRoom> hotelRoom = hotelRoomRepository.findById(hotelRoomId);
        if (hotelRoom.isEmpty()) {
            log.info("deleteHotelRoom: " + hotelRoomId + " not found");
        } else {
            hotelRoomRepository.delete(hotelRoom.get());
        }
    }

    public List<HotelRoomDto> getHotelRooms() {
        List<HotelRoom> hotelRooms = hotelRoomRepository.findAll();
        List<HotelRoomDto> hotelRoomDtos = new ArrayList<>();
        for (HotelRoom hotelRoom : hotelRooms) {
            hotelRoomDtos.add(modelMapper.map(hotelRoom, HotelRoomDto.class));
        }
        return hotelRoomDtos;
    }

    /**
     * 호텔 아이디로 호텔 방 정보 가져오기
     *
     * @param hotelId 호텔 아이디
     * @return 호텔 방 정보
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
     * @return 호텔 방 정보
     * @author 김남주
     */
    public HotelRoomDto updateHotelRoom(HotelRoomDto hotelRoomDto) {
        HotelRoom hotelRoom = hotelRoomRepository.findById(hotelRoomDto.getIdx()).orElseThrow();

        hotelRoom.setRoomNumber(hotelRoomDto.getRoomNumber());
        hotelRoom.setRoomType(hotelRoomTypeRepository.findById(hotelRoomDto.getRoomTypeIdx()).get());

        return modelMapper.map(hotelRoom, HotelRoomDto.class);
    }


    public void initHotelRoomData(List<HotelRoom> hotelRooms) {
        hotelRoomRepository.saveAll(hotelRooms);

        for(HotelRoom hotelRoom : hotelRoomRepository.findAll()) {
            log.info("initHotelRoomData: " + gson.toJson(hotelRoom));
        }

        log.info("initHotelRoomData completed");
    }



}
