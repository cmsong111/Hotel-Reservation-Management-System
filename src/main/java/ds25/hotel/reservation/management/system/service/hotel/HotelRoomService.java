package ds25.hotel.reservation.management.system.service.hotel;


import ds25.hotel.reservation.management.system.dto.hotel.HotelDto;
import ds25.hotel.reservation.management.system.dto.hotel.HotelRoomDto;
import ds25.hotel.reservation.management.system.entity.hotel.HotelRoom;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRoomImageRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HotelRoomService {
    HotelRoomRepository hotelRoomRepository;
    HotelRoomImageRepository hotelRoomImageRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public HotelRoomService(HotelRoomRepository hotelRoomRepository, HotelRoomImageRepository hotelRoomImageRepository) {
        this.hotelRoomRepository = hotelRoomRepository;
        this.hotelRoomImageRepository = hotelRoomImageRepository;
    }


    /**
     * 호텔 객실 추가
     *
     * @param hotelRoomDto 호텔 객실 정보
     * @return 호텔 객실 정보
     * @throws Exception 예외 처리
     * @author 김남주
     */
    public HotelRoomDto addHotelRoom(HotelRoomDto hotelRoomDto) throws Exception {

        if (hotelRoomDto.getName() == null) {
            throw new Exception("호텔 객실 이름이 없습니다");
        } else if (hotelRoomDto.getRoomCount() == 0) {
            throw new Exception("호텔 객실 개수가 없습니다");
        } else if (hotelRoomDto.getPrice() == 0) {
            throw new Exception("호텔 객실 가격이 없습니다");
        } else if (hotelRoomDto.getPeopleCount() == 0) {
            throw new Exception("호텔 객실 인원수가 없습니다");
        }

        HotelRoom hotelRoom = modelMapper.map(hotelRoomDto, HotelRoom.class);
        return modelMapper.map(hotelRoomRepository.save(hotelRoom), HotelRoomDto.class);
    }

    /**
     * 호텔 객실 정보 수정
     *
     * @param hotelRoomDto 호텔 객실 정보
     * @throws Exception 예외 처리
     * @author 김남주
     */
    public HotelRoomDto modifyHotelRoom(HotelRoomDto hotelRoomDto) throws Exception {
        Optional<HotelRoom> oldHotelRoom = hotelRoomRepository.findById(hotelRoomDto.getIdx());
        if (oldHotelRoom.isEmpty()) {
            throw new Exception("존재하지 않는 호텔 객실 정보입니다");
        }
        if (hotelRoomDto.getName() != null) {
            oldHotelRoom.get().setName(hotelRoomDto.getName());
        }
        if (hotelRoomDto.getDescription() != null) {
            oldHotelRoom.get().setDescription(hotelRoomDto.getDescription());
        }
        if (hotelRoomDto.getPrice() != 0) {
            oldHotelRoom.get().setPrice(hotelRoomDto.getPrice());
        }
        if (hotelRoomDto.getDiscount() != 0) {
            oldHotelRoom.get().setDiscount(hotelRoomDto.getDiscount());
        }
        if (hotelRoomDto.getRoomCount() != 0) {
            oldHotelRoom.get().setRoomCount(hotelRoomDto.getRoomCount());
        }
        if (hotelRoomDto.getPeopleCount() != 0) {
            oldHotelRoom.get().setPeopleCount(hotelRoomDto.getPeopleCount());
        }
        if (hotelRoomDto.getBedSize() != null) {
            oldHotelRoom.get().setBedSize(hotelRoomDto.getBedSize());
        }

        return modelMapper.map(hotelRoomRepository.save(oldHotelRoom.get()), HotelRoomDto.class);
    }

    /**
     * 호텔 객실 삭제
     *
     * @param hotelRoomDto 호텔 객실 정보
     * @throws Exception 예외 처리
     * @author 김남주
     */
    public void removeHotelRoom(HotelRoomDto hotelRoomDto) throws Exception {
        log.info("remove HotelRoom Service");
        Optional<HotelRoom> oldHotelRoom = hotelRoomRepository.findById(hotelRoomDto.getIdx());
        if (oldHotelRoom.isEmpty()) {
            throw new Exception("존재하지 않는 호텔 객실 정보입니다");
        }
        hotelRoomRepository.delete(oldHotelRoom.get());
        log.info("호텔 객실 정보가 삭제되었습니다");
    }

    /**
     * 호텔 객실 정보 조회
     *
     * @param hotelRoomIdx 호텔 객실 index 번호
     * @return 호텔 객실 정보
     * @author 김남주
     */
    public Optional<HotelRoomDto> findHotelRoomByIdx(Long hotelRoomIdx) {
        Optional<HotelRoom> hotelRoom = hotelRoomRepository.findById(hotelRoomIdx);
        if (hotelRoom.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(modelMapper.map(hotelRoom.get(), HotelRoomDto.class));
        }
    }

    /**
     * 호텔 객실 정보 조회
     *
     * @param hotelIdx 호텔 index 번호
     * @return 호텔 객실 정보
     * @author 김남주
     */
    public ArrayList<HotelRoomDto> findHotelRoomByHotelIdx(Long hotelIdx) {
        List<HotelRoom> hotelRooms = hotelRoomRepository.findByHotel_Idx(hotelIdx);
        ArrayList<HotelRoomDto> hotelRoomDtos = new ArrayList<>();
        for (HotelRoom hotelRoom : hotelRooms) {
            hotelRoomDtos.add(modelMapper.map(hotelRoom, HotelRoomDto.class));
        }
        return hotelRoomDtos;
    }
}
