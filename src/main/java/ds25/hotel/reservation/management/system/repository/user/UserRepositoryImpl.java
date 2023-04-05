package ds25.hotel.reservation.management.system.repository.user;

import ds25.hotel.reservation.management.system.entity.user.User;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public class UserRepositoryImpl extends SimpleJpaRepository<User, String> implements UserRepository {

    private final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        super(User.class, entityManager);
        this.entityManager = entityManager;
    }




}
