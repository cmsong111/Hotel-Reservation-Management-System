package ds25.hotel.reservation.management.system.repository.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.HotelReservation;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class HotelReservationRepositoryImpl extends SimpleJpaRepository<HotelReservation, Long> implements HotelReservationRepository {

    private final EntityManager entityManager;

    public HotelReservationRepositoryImpl(EntityManager entityManager) {
        super(HotelReservation.class, entityManager);
        this.entityManager = entityManager;
    }

    public List<HotelReservation> findByHotel_Idx(Long idx) {
        List<HotelReservation> hotelReservations = findAll();
        List<HotelReservation> result = new ArrayList<>();
        for (HotelReservation hotelReservation : hotelReservations) {
            if (Objects.equals(hotelReservation.getHotel().getIdx(), idx)) {
                result.add(hotelReservation);
            }
        }
        return result;
    }


    public List<HotelReservation> findByHotelReservationsByRoomIdxAndTime(int roomIdx, Timestamp startDate, Timestamp endDate) {

        List<HotelReservation> hotelReservations = findAll();
        List<HotelReservation> result = new ArrayList<>();

        for (HotelReservation hotelReservation : hotelReservations) {
            if (hotelReservation.getCheckInDate().after(startDate) && hotelReservation.getCheckOutDate().before(endDate) && hotelReservation.getHotelRoom().getIdx() == roomIdx) {
                result.add(hotelReservation);
            }
        }
        return result;
    }

    public List<HotelReservation> findByUser_Id(String id) {
        List<HotelReservation> hotelReservations = findAll();
        List<HotelReservation> result = new ArrayList<>();
        for (HotelReservation hotelReservation : hotelReservations) {
            if (hotelReservation.getUser().getId().equals(id)) {
                result.add(hotelReservation);
            }
        }
        return result;
    }
}
