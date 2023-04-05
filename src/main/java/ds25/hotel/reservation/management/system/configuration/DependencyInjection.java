package ds25.hotel.reservation.management.system.configuration;

import ds25.hotel.reservation.management.system.repository.hotel.*;
import ds25.hotel.reservation.management.system.repository.user.UserRepository;
import ds25.hotel.reservation.management.system.repository.user.UserRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceProvider;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class DependencyInjection {

    private static final DependencyInjection instance = new DependencyInjection();

    public static DependencyInjection getInstance() {
        return instance;
    }

    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final HotelRoomRepository hotelRoomRepository;
    private final HotelReservationRepository hotelReservationRepository;

    private DependencyInjection() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("jakarta.persistence.jdbc.driver", "org.h2.Driver");
        properties.put("jakarta.persistence.jdbc.url", "jdbc:h2:mem:test;MODE=MySQL");
        properties.put("jakarta.persistence.jdbc.user", "sa");
        properties.put("jakarta.persistence.jdbc.password", "");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.use_sql_comments", "true");
        properties.put("hibernate.id.new_generator_mappings", "true");

        properties.put("hibernate.hbm2ddl.auto", "create");


        PersistenceProvider provider = new HibernatePersistenceProvider();
        EntityManagerFactory emf = provider.createEntityManagerFactory("Hotel", properties);

        EntityManager em = emf.createEntityManager();

        userRepository = new UserRepositoryImpl(em);
        hotelRepository = new HotelRepositoryImpl(em);
        hotelReservationRepository = new HotelReservationRepositoryImpl(em);
        hotelRoomRepository = new HotelRoomRepositoryImpl(em);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public HotelRepository getHotelRepository() {
        return hotelRepository;
    }

    public HotelReservationRepository getHotelReservationRepository() {
        return hotelReservationRepository;
    }

    public HotelRoomRepository getHotelRoomRepository() {
        return hotelRoomRepository;
    }
}
