package ds25.hotel.reservation.management.system.util.strategy;

import ds25.hotel.reservation.management.system.domain.hotel.Hotel;

public class HotelSortByNameAcs implements Comparator<Hotel> {
	@Override
	public int compare(Hotel o1, Hotel o2) {
		return o1.getName().compareTo(o2.getName());
	}
}
