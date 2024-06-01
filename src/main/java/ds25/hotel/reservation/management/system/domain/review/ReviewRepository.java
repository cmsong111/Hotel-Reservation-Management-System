package ds25.hotel.reservation.management.system.domain.review;


import java.util.List;
import java.util.Optional;


public interface ReviewRepository {
	Review save(Review review);

	Optional<Review> findById(Long id);

	List<Review> findByHotel(Long id);

	List<Review> findByAuthor(Long id);
}

