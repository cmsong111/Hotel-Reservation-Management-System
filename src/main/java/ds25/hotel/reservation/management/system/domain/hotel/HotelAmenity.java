package ds25.hotel.reservation.management.system.domain.hotel;

import lombok.Getter;

@Getter
public enum HotelAmenity {
	BATHTUB("욕조"),
	BIDET("비데"),
	SHOWER("샤워기"),
	BATHROBE("목욕 가운"),
	DRYER("헤어 드라이어"),
	WIFI("와이파이"),
	TELEVISION("텔레비전"),
	REFRIGERATOR("냉장고"),
	AIR_CONDITIONER("에어컨"),
	PARKING_AVAILABLE("주차 가능"),
	SWIMMING_POOL("수영장"),
	BREAKFAST("조식 제공"),
	TERRACE("테라스"),
	SAUNA("사우나"),
	RESTAURANT("레스토랑"),
	BAR("바"),
	FITNESS_CENTER("피트니스 센터"),
	BUFFET("뷔페"),
	DESK_24H("24시간 데스크"),
	LENDING_NOTEBOOK("노트북 대여"),
	COFFEE_SHOP("커피숍"),
	BUSINESS_CENTER("비즈니스 센터"),
	BAGGAGE_STORAGE("수하물 보관");

	private final String description;

	HotelAmenity(String description) {
		this.description = description;
	}
}
