package ds25.hotel.reservation.management.system.service.hotel;


import ds25.hotel.reservation.management.system.dto.hotel.HotelRoomTypeDto;
import ds25.hotel.reservation.management.system.dto.hotel.HotelRoomTypeImageDto;
import ds25.hotel.reservation.management.system.entity.hotel.HotelRoomType;
import ds25.hotel.reservation.management.system.entity.hotel.HotelRoomTypeImage;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRoomTypeImageRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRoomTypeRepository;
import ds25.hotel.reservation.management.system.domain.user.dao.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    public HotelRoomTypeService(HotelRoomTypeRepository hotelRoomTypeRepository, UserRepository userRepository, HotelRepository hotelRepository, HotelRoomTypeImageRepository hotelRoomTypeImageRepository) {
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
    public HotelRoomTypeDto addHotelRoomType(HotelRoomTypeDto hotelRoomTypeDto) throws Exception {

        if (hotelRoomTypeDto.getName() == null || hotelRoomTypeDto.getName().equals("")) {
            throw new Exception("호텔 객실 이름이 없습니다");
        } else if (hotelRoomTypeDto.getRoomCount() == 0) {
            throw new Exception("호텔 객실 개수가 없습니다");
        } else if (hotelRoomTypeDto.getPrice() == 0) {
            throw new Exception("호텔 객실 가격이 없습니다");
        } else if (hotelRoomTypeDto.getPeopleCount() == 0) {
            throw new Exception("호텔 객실 인원수가 없습니다");
        }

        HotelRoomType hotelRoomType = hotelRoomTypeRepository.save(modelMapper.map(hotelRoomTypeDto, HotelRoomType.class));

        return convertToDto(hotelRoomType);
    }

    /**
     * 호텔 객실 정보 수정
     *
     * @param hotelRoomTypeDto 호텔 객실 정보
     * @throws Exception 예외 처리
     * @author 김남주
     */
    public HotelRoomTypeDto modifyHotelRoomType(HotelRoomTypeDto hotelRoomTypeDto) throws Exception {
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
        HotelRoomType hotelRoomType = hotelRoomTypeRepository.save(oldHotelRoom.get());

        return convertToDto(hotelRoomType);
    }

    /**
     * 호텔 객실 삭제
     *
     * @param hotelRoomTypeDto 호텔 객실 정보
     * @throws Exception 예외 처리
     * @author 김남주
     */
    public void removeHotelRoomType(HotelRoomTypeDto hotelRoomTypeDto) throws Exception {
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

        return Optional.of(convertToDto(hotelRoomType.get()));
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
            hotelRoomTypeDtos.add(convertToDto(hotelRoomType));
        }
        return hotelRoomTypeDtos;
    }

    /**
     * 호텔 객실 사진 등록
     *
     * @param imageDtos        호텔 객실 사진 정보 DTO (새 사진일 경우 idx is null, 기존 사진일 수정인 경우 idx 존재)
     * @param hotelRoomTypeIdx 호텔 객실 타입 index 번호
     */
    public void modifyHotelRoomTypePhoto(ArrayList<HotelRoomTypeImageDto> imageDtos, Long hotelRoomTypeIdx) {
        HotelRoomType hotelRoomType = hotelRoomTypeRepository.findById(hotelRoomTypeIdx).orElseThrow();

        for (HotelRoomTypeImageDto imageDto : imageDtos) {
            HotelRoomTypeImage hotelRoomTypeImage = modelMapper.map(imageDto, HotelRoomTypeImage.class);
            hotelRoomTypeImage.setRoomType(hotelRoomType);
            hotelRoomTypeImageRepository.save(hotelRoomTypeImage);
        }
        log.info("호텔 객실 사진이 등록되었습니다");
    }

    private HotelRoomTypeDto convertToDto(HotelRoomType hotelRoomType) {
        HotelRoomTypeDto hotelRoomTypeDto = modelMapper.map(hotelRoomType, HotelRoomTypeDto.class);

        ArrayList<HotelRoomTypeImageDto> hotelRoomTypeImageDtos = new ArrayList<>();
        for (HotelRoomTypeImage hotelRoomTypeImage : hotelRoomTypeImageRepository.findByRoomType_Idx(hotelRoomType.getIdx())) {
            hotelRoomTypeImageDtos.add(modelMapper.map(hotelRoomTypeImage, HotelRoomTypeImageDto.class));
        }
        // 이미지가 없을 경우 기본 이미지 추가
        if (hotelRoomTypeImageDtos.isEmpty()) {
            log.info("호텔 객실 이미지가 존재하지 않습니다");
            hotelRoomTypeImageDtos.add(new HotelRoomTypeImageDto(1L, "https://lh6.googleusercontent.com/Bu-pRqU_tWZV7O3rJ5nV1P6NjqFnnAs8kVLC5VGz_Kf7ws0nDUXoGTc7pP87tyUCfu8VyXi0YviIm7CxAISDr2lJSwWwXQxxz98qxVfMcKTJfLPqbcfhn-QEeOowjrlwX1LYDFJN"));
        }
        hotelRoomTypeDto.setImages(hotelRoomTypeImageDtos);
        return hotelRoomTypeDto;
    }

}
