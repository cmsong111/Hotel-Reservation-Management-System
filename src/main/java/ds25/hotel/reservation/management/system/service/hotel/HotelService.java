package ds25.hotel.reservation.management.system.service.hotel;

import com.google.gson.Gson;
import ds25.hotel.reservation.management.system.dto.hotel.HotelDto;
import ds25.hotel.reservation.management.system.dto.hotel.HotelImageDto;
import ds25.hotel.reservation.management.system.entity.hotel.Hotel;
import ds25.hotel.reservation.management.system.entity.hotel.HotelImage;
import ds25.hotel.reservation.management.system.repository.hotel.HotelImageRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelReservationRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRoomTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    HotelImageRepository hotelImageRepository;
    HotelRoomTypeRepository hotelRoomTypeRepository;
    HotelReservationRepository hotelReservationRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public HotelService(HotelRepository hotelRepository, HotelImageRepository hotelImageRepository, HotelRoomTypeRepository hotelRoomTypeRepository, HotelReservationRepository hotelReservationRepository) {
        this.hotelRepository = hotelRepository;
        this.hotelImageRepository = hotelImageRepository;
        this.hotelRoomTypeRepository = hotelRoomTypeRepository;
        this.hotelReservationRepository = hotelReservationRepository;
    }


    /**
     * 호텔 추가
     *
     * @param hotel 호텔 정보
     * @return 호텔 정보
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public HotelDto addHotel(HotelDto hotelDto) throws Exception {

        if (hotelDto.getName() == null) {
            throw new Exception("호텔 이름이 없습니다");
        } else if (hotelDto.getPhone() == null) {
            throw new Exception("호텔 전화번호가 없습니다");
        } else if (hotelDto.getAddress() == null) {
            throw new Exception("호텔 주소가 없습니다");
        }
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        return modelMapper.map(hotelRepository.save(hotel), HotelDto.class);
    }

    /**
     * 호텔 삭제
     *
     * @param hotelDto 호텔 정보
     * @throws Exception 예외처리
     * @author 김남주
     */
    public void removeHotel(HotelDto hotelDto) throws Exception {
        Optional<Hotel> hotel = hotelRepository.findById(hotelDto.getIdx());

        if (hotel.isPresent()) {

            hotelRepository.deleteById(hotelDto.getIdx());
            log.info("호텔 정보가 삭제되었습니다");
        } else {
            throw new Exception("삭제할 호텔 정보가 없습니다");
        }
    }

    /**
     * 호텔 정보 수정
     *
     * @param hotelDto 호텔 정보
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public HotelDto modifyHotel(HotelDto hotelDto) throws Exception {
        Optional<Hotel> oldHotel = hotelRepository.findById(hotelDto.getIdx());
        if (oldHotel.isEmpty()) {
            throw new Exception("수정할 호텔 정보가 없습니다");
        }
        if (hotelDto.getName() != null) {
            oldHotel.get().setName(hotelDto.getName());
        }
        if (hotelDto.getAddress() != null) {
            oldHotel.get().setAddress(hotelDto.getAddress());
        }
        if (hotelDto.getPhone() != null) {
            oldHotel.get().setPhone(hotelDto.getPhone());
        }
        if (hotelDto.getPhone() != null) {
            oldHotel.get().setPhone(hotelDto.getPhone());
        }
        if (hotelDto.getDescription() != null) {
            oldHotel.get().setDescription(hotelDto.getDescription());
        }

        return modelMapper.map(hotelRepository.save(oldHotel.get()), HotelDto.class);
    }

    /**
     * 호텔 번호로 정보 조회
     *
     * @param idx 호텔 번호
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public Optional<HotelDto> findHotel(long idx) {
        Optional<Hotel> hotel = hotelRepository.findById(idx);

        if (hotel.isPresent()) {
            return Optional.of(convertToDto(hotel.get()));
        } else {
            return Optional.empty();
        }
    }

    /**
     * 호텔 전체 목록 조회
     *
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public List<HotelDto> findAllHotel() {
        log.info("findAllHotel method called");
        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelDto> hotelDto = new ArrayList<>();
        for (Hotel hotel : hotels) {
            hotelDto.add(convertToDto(hotel));
        }
        return hotelDto;
    }

    /**
     * 호텔 이름으로 정보 조회
     *
     * @param name 호텔 이름
     * @throws IOException 파일 입출력 예외
     * @author 김남주
     */
    public List<HotelDto> getHotelByName(String name) {
        log.info("getHotelByName method called, keyword is " + name);
        List<Hotel> hotels = hotelRepository.findByName(name);
        List<HotelDto> hotelDto = new ArrayList<>();
        for (Hotel hotel : hotels) {
            hotelDto.add(convertToDto(hotel));
        }
        return hotelDto;
    }

    public void initHotelData(List<Hotel> hotels) {
        log.info("initializeData method called");
        for (Hotel hotel : hotels) {
            List<HotelImage> saved = new ArrayList<>();
            for(HotelImage hotelImage : hotel.getImages()){
                saved.add(hotelImageRepository.save(hotelImage));
            }
            hotel.setImages(saved);
            hotelRepository.save(hotel);
        }

        for (Hotel hotel: hotelRepository.findAll()) {
            log.info("hotel 입력 완. : {}", hotel.toString());
        }

    }

    private HotelDto convertToDto(Hotel hotel) {
        HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);
        ArrayList<HotelImageDto> hotelImageDtos = new ArrayList<>();
        for (HotelImage hotelImage : hotelImageRepository.findByHotel_Idx(hotel.getIdx())) {
            hotelImageDtos.add(modelMapper.map(hotelImage, HotelImageDto.class));
        }
        hotelDto.setImages(hotelImageDtos);
        return hotelDto;
    }

}
