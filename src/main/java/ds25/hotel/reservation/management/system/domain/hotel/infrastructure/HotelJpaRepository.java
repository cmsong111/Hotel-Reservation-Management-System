package ds25.hotel.reservation.management.system.domain.hotel.infrastructure;

import ds25.hotel.reservation.management.system.di.Repository;
import ds25.hotel.reservation.management.system.domain.hotel.Hotel;
import ds25.hotel.reservation.management.system.domain.hotel.HotelRepository;
import ds25.hotel.reservation.management.system.util.JpaTemplate;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;


@Repository
public class HotelJpaRepository implements HotelRepository {

	private final JpaTemplate jpaTemplate;

	@Inject
	public HotelJpaRepository(EntityManagerFactory emf) {
		this.jpaTemplate = new JpaTemplate(emf);
	}

	@Override
	public Hotel save(Hotel hotel) {
		return jpaTemplate.execute(em -> {
			if (hotel.getId() == null) {
				em.persist(hotel);
			} else {
				em.merge(hotel);
			}
			return hotel;
		});
	}

	@Override
	public List<Hotel> findByNameLike(String name) {
		return jpaTemplate.executeReadOnly(
				em -> em.createQuery("SELECT h FROM Hotel h WHERE h.name LIKE :name", Hotel.class)
						.setParameter("name", "%" + name + "%")
						.getResultList()
		);
	}

	@Override
	public List<Hotel> findAll() {
		return jpaTemplate.executeReadOnly(
				em -> em.createQuery("SELECT h FROM Hotel h", Hotel.class)
						.getResultList()
		);
	}

	@Override
	public Optional<Hotel> findById(Long id) {
		return jpaTemplate.executeReadOnly(
				em -> {
					Hotel hotel = em.find(Hotel.class, id);
					return Optional.ofNullable(hotel);
				}
		);
	}

	@Override
	public void deleteById(Long id) {
		jpaTemplate.execute(em -> {
			Hotel hotel = em.find(Hotel.class, id);
			if (hotel != null) {
				em.remove(hotel);
			}
			return null;
		});
	}
}
