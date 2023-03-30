package gs25.hotel.reservation.management.system.observer;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class LoginStatus implements Observable {
    private ArrayList<Observer> obervers = new ArrayList<Observer>();
    boolean isLogin = false;

    public void changeStatus(boolean isLogin) {
        this.isLogin = isLogin;
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
            obs.update(null, isLogin);
        }
    }
}
