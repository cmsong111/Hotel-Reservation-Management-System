package ds25.hotel.reservation.management.system.pattern.command;

import ds25.hotel.reservation.management.system.global.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.hotel.HotelReservationDto;
import ds25.hotel.reservation.management.system.entity.hotel.HotelReservation;
import ds25.hotel.reservation.management.system.repository.hotel.HotelReservationRepository;
import ds25.hotel.reservation.management.system.repository.hotel.HotelRoomRepository;
import ds25.hotel.reservation.management.system.domain.user.dao.UserRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;
import java.util.Optional;

@Slf4j
public class HotelReservationCommand {
    @Getter
    @Setter
    public HotelReservationDto hotelReservationDto;
    private final HotelReservationRepository  hotelReservationRepository = SpringBridge.getInstance().getBean(HotelReservationRepository.class);
    private final HotelRoomRepository hotelRoomRepository= SpringBridge.getInstance().getBean(HotelRoomRepository.class);
    private final UserRepository userRepository= SpringBridge.getInstance().getBean(UserRepository.class);

    ModelMapper modelMapper = new ModelMapper();

    public HotelReservationCommand(HotelReservationDto hotelReservationDto) {
        this.hotelReservationDto = hotelReservationDto;
    }

    public void pay(int price) {
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
        hotelReservation.setUser(userRepository.findByEmail(hotelReservationDto.getUserId()));
        hotelReservation.setHotelRoom(hotelRoomRepository.findById(hotelReservationDto.getHotelRoomIdx()).get());
        hotelReservation.setCheckInDate(hotelReservationDto.getCheckInDate());
        hotelReservation.setCheckOutDate(hotelReservationDto.getCheckOutDate());
        hotelReservation.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        hotelReservation.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        hotelReservation.setPayedMoney(hotelReservation.getPayedMoney()+price);

        this.hotelReservationDto = modelMapper.map(hotelReservationRepository.save(hotelReservation), HotelReservationDto.class);
    }

    public void cancle() {
        Optional<HotelReservation> oldHotelReservation = hotelReservationRepository.findById(hotelReservationDto.getIdx());
        if (oldHotelReservation.isPresent()) {
            log.info("호텔 예약이 삭제되었습니다");
            hotelReservationRepository.delete(oldHotelReservation.get());
        } else {
            log.error("존재하지 않는 호텔 예약 정보입니다");
        }
        this.hotelReservationDto = null;
    }
}
