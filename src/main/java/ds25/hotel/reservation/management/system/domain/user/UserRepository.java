package ds25.hotel.reservation.management.system.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
	User save(User user);

	boolean existsByEmail(String email);

	Optional<User> findById(Long id);

	Optional<User> findByEmail(String email);

	List<User> findAll();
}
