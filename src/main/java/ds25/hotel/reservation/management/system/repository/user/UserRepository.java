package ds25.hotel.reservation.management.system.repository.user;

import ds25.hotel.reservation.management.system.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsById(String id);

    Optional<User> findByIdAndPassword(String id, String password);

    List<User> findAll();
}
