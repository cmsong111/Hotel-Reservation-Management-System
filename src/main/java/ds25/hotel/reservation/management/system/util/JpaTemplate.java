package ds25.hotel.reservation.management.system.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.function.Function;

public class JpaTemplate {
	private final EntityManagerFactory emf;

	public JpaTemplate(EntityManagerFactory emf) {
		this.emf = emf;
	}

	/**
	 * 데이터 변경(INSERT, UPDATE, DELETE)이 있는 트랜잭션 작업을 위한 템플릿
	 */
	public <T> T execute(Function<EntityManager, T> action) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			T result = action.apply(em);
			tx.commit();
			return result;
		} catch (Exception e) {
			if (tx.isActive()) tx.rollback();
			throw new RuntimeException("Transaction failed", e);
		} finally {
			em.close();
		}
	}

	/**
	 * 단순 조회(SELECT)처럼 트랜잭션이 필요 없는 작업을 위한 템플릿
	 */
	public <T> T executeReadOnly(Function<EntityManager, T> action) {
		EntityManager em = emf.createEntityManager();
		try {
			return action.apply(em);
		} finally {
			em.close();
		}
	}
}
