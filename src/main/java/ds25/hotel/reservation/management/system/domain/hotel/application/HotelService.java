package ds25.hotel.reservation.management.system.domain.hotel.application;

import ds25.hotel.reservation.management.system.di.Service;
import ds25.hotel.reservation.management.system.domain.hotel.Hotel;
import ds25.hotel.reservation.management.system.domain.hotel.HotelRepository;
import ds25.hotel.reservation.management.system.domain.hotel.HotelRoomTypeRepository;
import ds25.hotel.reservation.management.system.domain.hotel.application.data.HotelForm;
import ds25.hotel.reservation.management.system.domain.reservation.ReservationRepository;
import ds25.hotel.reservation.management.system.ui.dto.hotel.HotelDto;
import ds25.hotel.reservation.management.system.ui.dto.hotel.HotelImageDto;
import jakarta.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HotelService {
	private final HotelRepository hotelRepository;
	private final HotelRoomTypeRepository hotelRoomTypeRepository;
	private final ReservationRepository hotelReservationRepository;

	@Inject
	public HotelService(HotelRepository hotelRepository, HotelRoomTypeRepository hotelRoomTypeRepository, ReservationRepository hotelReservationRepository) {
		this.hotelRepository = hotelRepository;
		this.hotelRoomTypeRepository = hotelRoomTypeRepository;
		this.hotelReservationRepository = hotelReservationRepository;
	}

	/**
	 * 호텔 번호로 정보 조회
	 *
	 * @param idx 호텔 번호
	 * @throws IOException 파일 입출력 예외
	 * @author 김남주
	 */
	public HotelDto findHotel(long idx) {
		Optional<Hotel> hotel = hotelRepository.findById(idx);

		return HotelDto.from(hotel.get());
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
			hotelDto.add(HotelDto.from(hotel));
		}
		return hotelDto;
	}

	/**
	 * 호텔 이름으로 검색
	 *
	 * @param name 호텔 이름
	 * @author 김남주
	 */
	public List<HotelDto> findHotelByName(String name) {
		log.info("getHotelByName method called, keyword is " + name);
		List<Hotel> hotels = hotelRepository.findByNameLike(name);
//		List<HotelDto> hotelDto = new ArrayList<>();
//		for (Hotel hotel : hotels) {
//			hotelDto.add(HotelDto.from(hotel));
//		}

		return hotelDto;
	}

	public void deleteHotel(Long idx) {
		hotelRepository.deleteById(idx);
	}


	/**
	 * 호텔 생성 메소드
	 *
	 * @param hotelDto 호텔 정보
	 * @return 호텔 정보
	 * @throws IllegalArgumentException 비어있음
	 * @author 김남주
	 */
	public HotelDto addHotel(HotelDto hotelDto) throws IllegalArgumentException {
		log.info("호텔 생성 메소드 실행");
		if (hotelDto.getName() == null) {
			throw new IllegalArgumentException("호텔 이름이 없습니다");
		} else if (hotelDto.getPhone() == null) {
			throw new IllegalArgumentException("호텔 전화번호가 없습니다");
		} else if (hotelDto.getAddress() == null) {
			throw new IllegalArgumentException("호텔 주소가 없습니다");
		}
		Hotel hotel = hotelRepository.save(modelMapper.map(hotelDto, Hotel.class));

		for (HotelImageDto hotelImageDto : hotelDto.getImages()) {
			HotelImage hotelImage = modelMapper.map(hotelImageDto, HotelImage.class);
			hotelImage.setHotel(hotel);
			hotelImageRepository.save(hotelImage);
		}
		return modelMapper.map(hotel, HotelDto.class);
	}

	/**
	 * 호텔 삭제
	 *
	 * @param hotelDto 호텔 정보
	 * @throws Exception 예외처리
	 * @author 김남주
	 */
	public void removeHotel(Long id) throws IllegalArgumentException {
		log.info("호텔 삭제 메소드 실행");
		Optional<Hotel> hotel = hotelRepository.findById(id);
		if (hotel.isEmpty()) {
			throw new IllegalArgumentException("호텔 정보가 없습니다");
		}
		hotelRoomTypeRepository.deleteByHotel_Id(id);
		hotelReservationRepository.deleteByHotel_Id(id);
		hotelRepository.deleteById(id);
		log.info("호텔 삭제 완료");
	}

	/**
	 * 호텔 정보 수정
	 *
	 * @param id        호텔 아이디
	 * @param hotelForm 호텔폼
	 * @author 김남주
	 */
	public HotelDto modifyHotel(Long id, HotelForm hotelForm) {
		Hotel oldHotel = hotelRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("호텔 정보가 없습니다"));

		oldHotel.update(
				hotelForm.name(),
				hotelForm.address(),
				hotelForm.phone(),
				hotelForm.email(),
				hotelForm.description(),
				hotelForm.images(),
				hotelForm.amenities());

		Hotel saveHotel = hotelRepository.save(oldHotel);

		return HotelDto.from(saveHotel);
	}
}
