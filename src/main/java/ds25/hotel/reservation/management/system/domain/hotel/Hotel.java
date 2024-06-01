package ds25.hotel.reservation.management.system.domain.hotel;

import ds25.hotel.reservation.management.system.util.attributeConverter.HotelAmenitySetConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OrderColumn;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String address;
	private String phone;
	private String email;

	@Column(columnDefinition = "LONGTEXT")
	private String description;

	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "hotel_images")
	@OrderColumn(name = "image_order")
	private List<String> images;

	@Convert(converter = HotelAmenitySetConverter.class)
	@Column(columnDefinition = "TEXT")
	private Set<HotelAmenity> amenities;

	/**
	 * 호텔 정보 업데이트
	 *
	 * @param name        이름
	 * @param address     주소
	 * @param phone       전화번호
	 * @param email       이메일
	 * @param description 설명
	 * @param amenities   편의시설
	 */
	public void update(String name,
					   String address,
					   String phone,
					   String email,
					   String description,
					   List<String> images,
					   Set<HotelAmenity> amenities) {
		if (!name.isBlank()) this.name = name;
		if (!address.isBlank()) this.address = address;
		if (!phone.isBlank()) this.phone = phone;
		if (!email.isBlank()) this.email = email;
		if (!description.isBlank()) this.description = description;
		if (images != null) this.images = images;
		if (amenities != null) this.amenities = amenities;
	}

}
