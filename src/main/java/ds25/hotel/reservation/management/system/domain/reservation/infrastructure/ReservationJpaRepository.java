package ds25.hotel.reservation.management.system.domain.reservation.infrastructure;

import ds25.hotel.reservation.management.system.di.Repository;
import ds25.hotel.reservation.management.system.domain.reservation.Reservation;
import ds25.hotel.reservation.management.system.domain.reservation.ReservationRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class ReservationJpaRepository implements ReservationRepository {

	final private EntityManagerFactory emf;

	@Inject
	public ReservationJpaRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<Reservation> findAll() {
		EntityManager em = null;
		try {
			// 1. 트랜젝션 시작
			em = emf.createEntityManager();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			// 메인 작업 넣기
			List<Reservation> reservations = em.createQuery("SELECT r FROM Reservation r", Reservation.class)
					.getResultList();

			// 3. 트랜젝션 커밋
			transaction.commit();
			return reservations;
		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}

	@Override
	public List<Reservation> findByHotelRoom_Idx(Long idx) {
		return List.of();
	}

	@Override
	public List<Reservation> findByHotelRoomType_Hotel_Idx(Long idx) {
		return List.of();
	}

	@Override
	public List<Reservation> findByHotelRoom_RoomType_Hotel_Idx(Long idx) {
		return List.of();
	}

	@Override
	public List<Reservation> findByUser_Id(String id) {
		return List.of();
	}

	@Override
	public long countByCheckInDateBetweenAndCheckOutDateBetweenAndHotelRoom_RoomType_Idx(Timestamp checkInDateStart, Timestamp checkInDateEnd, Timestamp checkOutDateStart, Timestamp checkOutDateEnd, Long idx) {
		return 0;
	}

	@Override
	public boolean existsByHotelRoomType_IdxAndCheckInDateBetweenAndCheckOutDateBetween(Long idx, Timestamp checkInDateStart, Timestamp checkInDateEnd, Timestamp checkOutDateStart, Timestamp checkOutDateEnd) {
		return false;
	}
}
