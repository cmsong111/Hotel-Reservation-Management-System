package gs25.hotel.reservation.management.system.service;

import gs25.hotel.reservation.management.system.configuration.Singleton;
import gs25.hotel.reservation.management.system.repository.UserRepository;

public class UserService {
    UserRepository userRepository = Singleton.getInstance().getUserRepository();

    public void init() {
        userRepository.loadFromJson();
    }
}
