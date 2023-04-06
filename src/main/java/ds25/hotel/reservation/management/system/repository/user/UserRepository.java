package ds25.hotel.reservation.management.system.repository.user;

import ds25.hotel.reservation.management.system.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsById(String id);

    List<User> findAll();
}
