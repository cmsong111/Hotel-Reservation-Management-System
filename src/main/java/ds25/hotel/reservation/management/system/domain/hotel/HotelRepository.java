package ds25.hotel.reservation.management.system.domain.hotel;

import java.util.List;
import java.util.Optional;


public interface HotelRepository {
	List<Hotel> findByNameLike(String name);

	List<Hotel> findAll();

	Optional<Hotel> findById(Long id);

	void deleteById(Long id);

	Hotel save(Hotel hotel);
}
