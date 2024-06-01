package ds25.hotel.reservation.management.system.util.factoryMethod;

import ds25.hotel.reservation.management.system.config.AppConfig;
import ds25.hotel.reservation.management.system.di.DiContext;
import ds25.hotel.reservation.management.system.domain.reservation.application.HotelReservationService;
import ds25.hotel.reservation.management.system.ui.dto.hotel.HotelReservationDto;

public class HotelReservationFactoryMethod {

	private static final HotelReservationService hotelReservationService = DiContext.getInstance().getComponent(HotelReservationService.class);

	public static HotelReservationDto createReservation(Long hotelIdx, Long hotelRoomTypeIdx) {

		HotelReservationDto reservation = HotelReservationDto.builder()
				.hotelIdx(hotelIdx)
				.userId(AppConfig.getInstance().getUser().getId())
				.hotelRoomTypeIdx(hotelRoomTypeIdx)
				.hotelRoomIdx(1L)
				.build();

		return hotelReservationService.createReservationID(reservation);
	}
}
