package gs25.hotel.reservation.management.system.pattern.observer;


public interface Observable {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
