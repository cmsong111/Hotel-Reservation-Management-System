package gs25.hotel.reservation.management.system.provider;

import gs25.hotel.reservation.management.system.entity.user.User;
import gs25.hotel.reservation.management.system.observer.Observable;
import gs25.hotel.reservation.management.system.observer.Observer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
public class UserProvider implements Observable {
    private ArrayList<Observer> obervers = new ArrayList<Observer>();
    Optional<User> user = Optional.empty();

    public void updateUser(User user) {
        log.info("유저 정보가 업데이트 되었습니다.");
        if (user == null) {
            this.user = Optional.empty();
        } else {
            this.user = Optional.of(user);
        }
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        log.info("옵저버가 등록되었습니다.");
        obervers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        log.info("옵저버가 삭제되었습니다.");
        int i = obervers.indexOf(o);
        if (i >= 0) {
            obervers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        log.info("옵저버에게 알립니다.");
        for (Observer observer : obervers) {
            Observer obs = (Observer) observer;
            obs.update(null, user);
        }
    }

    public Optional<User> getUser() {
        return user;
    }
}
