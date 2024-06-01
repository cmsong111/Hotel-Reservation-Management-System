package ds25.hotel.reservation.management.system.ui.dto.hotel;

import ds25.hotel.reservation.management.system.domain.hotel.Hotel;
import ds25.hotel.reservation.management.system.domain.hotel.HotelAmenity;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {
	private Long idx;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String description;
	private List<String> images;
	private Set<HotelAmenity> amenities;



	public static HotelDto from(Hotel hotel) {
		return HotelDto.builder()
				.idx(hotel.getId())
				.name(hotel.getName())
				.address(hotel.getAddress())
				.phone(hotel.getPhone())
				.email(hotel.getEmail())
				.description(hotel.getDescription())
				.images(hotel.getImages())
				.amenities(hotel.getAmenities())
				.build();
	}


}
