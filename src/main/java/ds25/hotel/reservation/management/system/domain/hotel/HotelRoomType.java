package ds25.hotel.reservation.management.system.domain.hotel;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.OrderColumn;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class HotelRoomType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;

	@ManyToOne(fetch = FetchType.LAZY)
	private Hotel hotel;

	private String name;
	@Column(columnDefinition = "LONGTEXT")
	private String description;
	private int price;
	private int discount;
	private int peopleCount;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(
			name = "room_type_beds", // 생성될 컬렉션 테이블의 이름
			joinColumns = @JoinColumn(name = "idx") // RoomType을 참조하는 외래 키
	)
	@MapKeyEnumerated(EnumType.STRING) // Map의 Key가 Enum 타입임을 명시
	@MapKeyColumn(name = "bed_type")   // Map의 Key를 저장할 컬럼 이름
	@Column(name = "quantity")         // Map의 Value를 저장할 컬럼 이름
	private Map<BedType, Integer> beds = new HashMap<>();


	private int roomSize;

	@CollectionTable(
			name = "hotel_room_type_image",
			joinColumns = @JoinColumn(name = "idx")
	)
	@OrderColumn(name = "image_order")
	private List<String> images;

	private List<HotelAmenity> service;
}
