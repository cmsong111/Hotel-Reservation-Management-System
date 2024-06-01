package ds25.hotel.reservation.management.system.domain.hotel.application.data;

import ds25.hotel.reservation.management.system.domain.hotel.HotelAmenity;
import java.util.List;
import java.util.Set;

public record HotelForm(
		String name,
		String address,
		String phone,
		String email,
		String description,
		List<String> images,
		Set<HotelAmenity> amenities
) {
}
