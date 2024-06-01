package ds25.hotel.reservation.management.system.domain.hotel.infrastructure;

import ds25.hotel.reservation.management.system.di.Repository;
import ds25.hotel.reservation.management.system.domain.hotel.Hotel;
import ds25.hotel.reservation.management.system.domain.hotel.HotelRoomType;
import ds25.hotel.reservation.management.system.domain.hotel.HotelRoomTypeRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;


@Repository
public class HotelRoomTypeJpaRepository implements HotelRoomTypeRepository {

	final private EntityManagerFactory emf;

	@Inject
	public HotelRoomTypeJpaRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	long deleteByHotel(Hotel hotel) {
		return null;
	}

	@Override
	List<HotelRoomType> findByHotel_Idx(Long idx) {
		return null;
	}

	@Override
	List<HotelRoomType> findByHotel_IdxAndNameContains(Long idx, String name) {
		return null;
	}
}


