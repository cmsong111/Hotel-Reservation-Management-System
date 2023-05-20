package ds25.hotel.reservation.management.system.provider;

import ds25.hotel.reservation.management.system.pattern.observer.Observable;
import ds25.hotel.reservation.management.system.pattern.observer.Observer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class HotelReservationProvider implements Observable {
    @Getter
    private ArrayList<Observer> obervers = new ArrayList<Observer>();

    @Override
    public void registerObserver(Observer o) {
        log.info("호텔 예약 옵저버가 등록되었습니다.");
        obervers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        log.info("호텔 예약 옵저버가 삭제되었습니다.");
        int i = obervers.indexOf(o);
        if (i >= 0) {
            obervers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        log.info("호텔 예약 옵저버에게 알립니다.");
        for (Observer observer : obervers) {
            observer.update(null, null);
        }
    }
}
