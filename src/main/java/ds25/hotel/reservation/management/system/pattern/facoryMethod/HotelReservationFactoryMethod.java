package ds25.hotel.reservation.management.system.pattern.facoryMethod;

import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.dto.hotel.HotelReservationDto;

public class HotelReservationFactoryMethod {

    public static HotelReservationDto createReservation(Long HotelRoomIdx) {

        return HotelReservationDto.builder()
                .hotelIdx(HotelRoomIdx)
                .userId(Singleton.getInstance().getUserProvider().getUser().get().getId())
                .peopleCount(2)
                .hotelRoomIdx(HotelRoomIdx)
                .build();

    }
}
