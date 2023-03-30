package gs25.hotel.reservation.management.system.observer;

import java.util.Observer;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
