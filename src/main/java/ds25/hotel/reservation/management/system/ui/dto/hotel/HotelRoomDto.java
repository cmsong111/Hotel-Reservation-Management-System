package ds25.hotel.reservation.management.system.ui.dto.hotel;


import ds25.hotel.reservation.management.system.domain.hotel.HotelRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelRoomDto {
	private Long idx;
	private Long roomNumber;
	private Long roomTypeIdx;

	static HotelRoomDto from(HotelRoom hotelRoom) {
		return HotelRoomDto.builder()
				.idx(hotelRoom.getIdx())
				.roomNumber(hotelRoom.getRoomNumber())
				.roomTypeIdx(hotelRoom.getIdx())
				.build();
	}
}
