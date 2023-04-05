package ds25.hotel.reservation.management.system.repository.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.HotelRoom;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HotelRoomRepositoryImpl extends SimpleJpaRepository<HotelRoom, Long> implements HotelRoomRepository {

    private final EntityManager entityManager;

    public HotelRoomRepositoryImpl(EntityManager entityManager) {
        super(HotelRoom.class, entityManager);
        this.entityManager = entityManager;
    }

    public List<HotelRoom> findByHotel_Idx(Long idx) {
        List<HotelRoom> hotelRooms = findAll();
        List<HotelRoom> hotelRoomList = new ArrayList<>();

        for (HotelRoom hotelRoom : hotelRooms) {
            if (Objects.equals(hotelRoom.getHotel().getIdx(), idx)) {
                hotelRoomList.add(hotelRoom);
            }
        }
        return hotelRoomList;
    }



}
