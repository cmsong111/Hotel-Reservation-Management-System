package ds25.hotel.reservation.management.system.domain.hotel.infrastructure;

import ds25.hotel.reservation.management.system.di.Repository;
import ds25.hotel.reservation.management.system.domain.hotel.HotelRoom;
import ds25.hotel.reservation.management.system.domain.hotel.HotelRoomRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;


@Repository
public class HotelRoomJpaRepository implements HotelRoomRepository {

	final private EntityManagerFactory emf;

	@Inject
	public HotelRoomJpaRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<HotelRoom> findByRoomType_Hotel_Idx(Long idx) {
		return List.of();
	}

	@Override
	public List<HotelRoom> findByRoomType_Idx(Long idx) {
		return List.of();
	}

	@Override
	public long countByRoomType_Idx(Long idx) {
		return 0;
	}
}
