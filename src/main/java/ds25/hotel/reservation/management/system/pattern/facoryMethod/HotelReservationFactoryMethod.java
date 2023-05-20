package ds25.hotel.reservation.management.system.pattern.facoryMethod;

import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.hotel.HotelReservationDto;
import ds25.hotel.reservation.management.system.service.hotel.HotelReservationService;

public class HotelReservationFactoryMethod {

    private static final HotelReservationService hotelReservationService = SpringBridge.getInstance().getBean(HotelReservationService.class);

    public static HotelReservationDto createReservation(Long hotelIdx, Long  hotelRoomTypeIdx) {



        return HotelReservationDto.builder()
                .hotelIdx(hotelIdx)
                .userId(Singleton.getInstance().getUser().getId())
                .peopleCount(2)
                .hotelRoomTypeIdx(hotelRoomTypeIdx)
                .build();

    }
}
