package ds25.hotel.reservation.management.system.repository.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.Hotel;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.ArrayList;
import java.util.List;

public class HotelRepositoryImpl extends SimpleJpaRepository<Hotel, Long> implements HotelRepository {
    private final EntityManager entityManager;

    public HotelRepositoryImpl(EntityManager entityManager) {
        super(Hotel.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public ArrayList<Hotel> findByName(String name) {
        List<Hotel> hotelList = findAll();
        ArrayList<Hotel> result = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            if (hotel.getName().contains(name)) {
                result.add(hotel);
            }
        }
        return result;
    }
}
