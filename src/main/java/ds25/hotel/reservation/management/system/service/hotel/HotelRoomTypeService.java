package ds25.hotel.reservation.management.system.service.hotel;


import com.google.gson.Gson;
import ds25.hotel.reservation.management.system.dto.hotel.HotelRoomTypeDto;
import ds25.hotel.reservation.management.system.dto.hotel.HotelRoomTypeImageDto;
import ds25.hotel.reservation.management.system.entity.hotel.HotelImage;
import ds25.hotel.reservation.management.system.entity.hotel.HotelRoomType;
import ds25.hotel.reservation.management.system.entity.hotel.HotelRoomTypeImage;
import ds25.hotel.reservation.management.system.repository.hotel.HotelImageRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRoomTypeImageRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRoomTypeRepository;
import ds25.hotel.reservation.management.system.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HotelRoomTypeService {
    private final HotelRoomTypeRepository hotelRoomTypeRepository;
    private final HotelRoomTypeImageRepository hotelRoomTypeImageRepository;
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    ModelMapper modelMapper = new ModelMapper();


    @Autowired
    public HotelRoomTypeService(HotelRoomTypeRepository hotelRoomTypeRepository, UserRepository userRepository,
                                HotelRepository hotelRepository,
                                HotelRoomTypeImageRepository hotelRoomTypeImageRepository) {
        this.hotelRoomTypeRepository = hotelRoomTypeRepository;
        this.userRepository = userRepository;
        this.hotelRepository = hotelRepository;
        this.hotelRoomTypeImageRepository = hotelRoomTypeImageRepository;
    }


    /**
     * 호텔 객실 추가
     *
     * @param hotelRoomTypeDto 호텔 객실 정보
     * @return 호텔 객실 정보
     * @throws Exception 예외 처리
     * @author 김남주
     */
    public HotelRoomTypeDto addHotelRoom(HotelRoomTypeDto hotelRoomTypeDto) throws Exception {

        if (hotelRoomTypeDto.getName() == null) {
            throw new Exception("호텔 객실 이름이 없습니다");
        } else if (hotelRoomTypeDto.getRoomCount() == 0) {
            throw new Exception("호텔 객실 개수가 없습니다");
        } else if (hotelRoomTypeDto.getPrice() == 0) {
            throw new Exception("호텔 객실 가격이 없습니다");
        } else if (hotelRoomTypeDto.getPeopleCount() == 0) {
            throw new Exception("호텔 객실 인원수가 없습니다");
        }

        HotelRoomType hotelRoomType = modelMapper.map(hotelRoomTypeDto, HotelRoomType.class);
        return modelMapper.map(hotelRoomTypeRepository.save(hotelRoomType), HotelRoomTypeDto.class);
    }

    /**
     * 호텔 객실 정보 수정
     *
     * @param hotelRoomTypeDto 호텔 객실 정보
     * @throws Exception 예외 처리
     * @author 김남주
     */
    public HotelRoomTypeDto modifyHotelRoom(HotelRoomTypeDto hotelRoomTypeDto) throws Exception {
        Optional<HotelRoomType> oldHotelRoom = hotelRoomTypeRepository.findById(hotelRoomTypeDto.getIdx());
        if (oldHotelRoom.isEmpty()) {
            throw new Exception("존재하지 않는 호텔 객실 정보입니다");
        }
        if (hotelRoomTypeDto.getName() != null) {
            oldHotelRoom.get().setName(hotelRoomTypeDto.getName());
        }
        if (hotelRoomTypeDto.getDescription() != null) {
            oldHotelRoom.get().setDescription(hotelRoomTypeDto.getDescription());
        }
        if (hotelRoomTypeDto.getPrice() != 0) {
            oldHotelRoom.get().setPrice(hotelRoomTypeDto.getPrice());
        }
        if (hotelRoomTypeDto.getDiscount() != 0) {
            oldHotelRoom.get().setDiscount(hotelRoomTypeDto.getDiscount());
        }
        if (hotelRoomTypeDto.getPeopleCount() != 0) {
            oldHotelRoom.get().setPeopleCount(hotelRoomTypeDto.getPeopleCount());
        }
        if (hotelRoomTypeDto.getBedSize() != null) {
            oldHotelRoom.get().setBedSize(hotelRoomTypeDto.getBedSize());
        }

        return modelMapper.map(hotelRoomTypeRepository.save(oldHotelRoom.get()), HotelRoomTypeDto.class);
    }

    /**
     * 호텔 객실 삭제
     *
     * @param hotelRoomTypeDto 호텔 객실 정보
     * @throws Exception 예외 처리
     * @author 김남주
     */
    public void removeHotelRoom(HotelRoomTypeDto hotelRoomTypeDto) throws Exception {
        log.info("remove HotelRoom Service");
        Optional<HotelRoomType> oldHotelRoom = hotelRoomTypeRepository.findById(hotelRoomTypeDto.getIdx());
        if (oldHotelRoom.isEmpty()) {
            throw new Exception("존재하지 않는 호텔 객실 정보입니다");
        }
        hotelRoomTypeRepository.delete(oldHotelRoom.get());
        log.info("호텔 객실 정보가 삭제되었습니다");
    }

    /**
     * 호텔 객실 정보 조회
     *
     * @param hotelRoomIdx 호텔 객실 타입 index 번호
     * @return 호텔 객실 정보
     * @author 김남주
     */
    public Optional<HotelRoomTypeDto> findHotelRoomByIdx(Long hotelRoomIdx) {
        log.info("호텔 객실 정보를 조회합니다. 검색 조건 : " + hotelRoomIdx);
        Optional<HotelRoomType> hotelRoomType = hotelRoomTypeRepository.findById(hotelRoomIdx);

        if (hotelRoomType.isEmpty()) {
            log.info("호텔 객실 정보가 없습니다");
            return Optional.empty();
        }
        // 이미지 불러오기
        log.info("호텔 객실 이미지를 불러옵니다");
        List<HotelRoomTypeImage> hotelRoomTypeImageList = hotelRoomTypeImageRepository.findByRoomType_Idx(hotelRoomIdx);
        ArrayList<HotelRoomTypeImageDto> hotelRoomTypeImageDtoList = new ArrayList<>();

        // 이미지 Entity -> Dto
        for (HotelRoomTypeImage hotelRoomTypeImage : hotelRoomTypeImageList) {
            HotelRoomTypeImageDto hotelRoomTypeImageDto = modelMapper.map(hotelRoomTypeImage, HotelRoomTypeImageDto.class);
            hotelRoomTypeImageDtoList.add(hotelRoomTypeImageDto);
        }

        // 이미지가 없을 경우 기본 이미지 추가
        if (hotelRoomTypeImageDtoList.isEmpty()) {
            log.info("호텔 객실 이미지가 존재하지 않습니다");
            hotelRoomTypeImageDtoList.add(new HotelRoomTypeImageDto(1L, "https://lh6.googleusercontent.com/Bu-pRqU_tWZV7O3rJ5nV1P6NjqFnnAs8kVLC5VGz_Kf7ws0nDUXoGTc7pP87tyUCfu8VyXi0YviIm7CxAISDr2lJSwWwXQxxz98qxVfMcKTJfLPqbcfhn-QEeOowjrlwX1LYDFJN"));
        }

        // 호텔 객실 Entity -> Dto
        HotelRoomTypeDto hotelTypeDto = modelMapper.map(hotelRoomType.get(), HotelRoomTypeDto.class);
        hotelTypeDto.setImages(hotelRoomTypeImageDtoList);
        log.info("호텔 객실 정보를 불러왔습니다,{}", hotelTypeDto.getName());
        return Optional.of(hotelTypeDto);
    }

    /**
     * 호텔 객실 정보 조회
     *
     * @param hotelIdx 호텔 index 번호
     * @return 호텔 객실 정보
     * @author 김남주
     */
    public ArrayList<HotelRoomTypeDto> findHotelRoomByHotelIdx(Long hotelIdx) {
        List<HotelRoomType> hotelRoomTypes = hotelRoomTypeRepository.findByHotel_Idx(hotelIdx);
        ArrayList<HotelRoomTypeDto> hotelRoomTypeDtos = new ArrayList<>();
        for (HotelRoomType hotelRoomType : hotelRoomTypes) {
            hotelRoomTypeDtos.add(modelMapper.map(hotelRoomType, HotelRoomTypeDto.class));
        }
        return hotelRoomTypeDtos;
    }

    public HotelRoomType getEntityFindById(Long idx) {
        return hotelRoomTypeRepository.findById(idx).get();
    }

//    public void initHotelRoomData(List<HotelRoomType> hotelRoomTypes) {
//        log.info("init HotelRoom Service");
//        for (HotelRoomType hotelRoomType : hotelRoomTypes){
//            List<HotelImage> saved = new ArrayList<>();
//            for(HotelRoomTypeImage hotelImage: hotelRoomType.getImages()){
//                saved.add(hotelRoomTypeImageRepository.save(hotelImage));
//            }
//            hotelRoomType.setImages(saved);
//            hotelRoomTypeRepository.save(hotelRoomType);
//        }
//
//        for (HotelRoomType hotelRoomType : hotelRoomTypeRepository.findAll()){
//            log.info("hotelRoomType : {} Saved ", hotelRoomType);
//        }
//    }
}
