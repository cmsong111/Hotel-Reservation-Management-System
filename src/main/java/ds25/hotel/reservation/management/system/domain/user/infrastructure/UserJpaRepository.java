package ds25.hotel.reservation.management.system.domain.user.infrastructure;

import ds25.hotel.reservation.management.system.di.Repository;
import ds25.hotel.reservation.management.system.domain.user.User;
import ds25.hotel.reservation.management.system.domain.user.UserRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.Optional;


@Repository
public class UserJpaRepository implements UserRepository {

	final private EntityManagerFactory emf;

	@Inject
	public UserJpaRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}
	@Override
	public User save(User user) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			if (user.getId() == null) {
				em.persist(user);
			} else {
				user = em.merge(user);
			}
			tx.commit();
			return user;
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw new RuntimeException("User 저장에 실패했습니다.", e);
		} finally {
			em.close();
		}
	}

	@Override
	public boolean existsByEmail(String email) {
		EntityManager em = emf.createEntityManager();
		try {
			// 전체 User 객체를 가져오는 대신 COUNT 쿼리를 사용하여 성능 최적화
			Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
					.setParameter("email", email)
					.getSingleResult();
			return count > 0;
		} finally {
			em.close();
		}
	}

	@Override
	public Optional<User> findById(Long id) {
		EntityManager em = emf.createEntityManager();
		try {
			// ID로 조회하는 가장 표준적인 방법
			User user = em.find(User.class, id);
			return Optional.ofNullable(user);
		} finally {
			em.close();
		}
	}

	@Override
	public Optional<User> findByEmail(String email) {
		EntityManager em = emf.createEntityManager();
		try {
			User user = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
					.setParameter("email", email)
					.getSingleResult();
			return Optional.of(user);
		} catch (NoResultException e) {
			// getSingleResult()는 결과가 없으면 예외를 던지므로, catch해서 empty Optional 반환
			return Optional.empty();
		} finally {
			em.close();
		}
	}

	@Override
	public List<User> findAll() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT u FROM User u", User.class)
					.getResultList();
		} finally {
			em.close();
		}
	}
}
