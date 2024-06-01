package ds25.hotel.reservation.management.system.domain.review.infrastructure;

import ds25.hotel.reservation.management.system.di.Repository;
import ds25.hotel.reservation.management.system.domain.review.Review;
import ds25.hotel.reservation.management.system.domain.review.ReviewRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

@Repository
public class ReviewJpaRepository implements ReviewRepository {
	final private EntityManagerFactory emf;

	@Inject
	public ReviewJpaRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Review save(Review review) {
		// 1. EntityManager는 try-catch 블록에 진입하기 전에 선언하는 것이 좋습니다.
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			// 2. persist/merge 로직을 먼저 수행
			if (review.getId() == null) {
				em.persist(review);
			} else {
				// merge는 영속 상태의 새로운 인스턴스를 반환하므로, 반환된 인스턴스를 사용해야 합니다.
				review = em.merge(review);
			}

			// 3. 로직이 성공적으로 끝나면 commit을 한 번만 호출
			tx.commit();
			return review;

		} catch (Exception e) {
			// 4. 예외 발생 시 롤백 후, 예외를 다시 던져서 호출 측에 문제를 알립니다.
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			// 구체적인 예외로 감싸서 던지는 것이 더 좋습니다.
			throw new RuntimeException("리뷰 저장에 실패했습니다.", e);

		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
	}

	@Override
	public Optional<Review> findById(Long id) {
		// 1. EntityManager 생성
		EntityManager em = emf.createEntityManager();
		try {
			Review review = em.find(Review.class, id);
			return Optional.ofNullable(review);
		} finally {
			// 4. EntityManager는 반드시 닫아줌
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}

	@Override
	public List<Review> findByHotel(Long id) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			String jpql = "SELECT r FROM Review r LEFT JOIN FETCH r.images WHERE r.hotelId = :id";
			return em.createQuery(jpql, Review.class)
					.setParameter("id", id)
					.getResultList();

		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}

	@Override
	public List<Review> findByAuthor(Long id) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			String jpql = "SELECT r FROM Review r LEFT JOIN FETCH r.images WHERE r.authorId = :id";
			return em.createQuery(jpql, Review.class)
					.setParameter("id", id)
					.getResultList();

		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}
}

