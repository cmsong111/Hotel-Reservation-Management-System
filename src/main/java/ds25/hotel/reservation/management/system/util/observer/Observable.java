
package ds25.hotel.reservation.management.system.util.observer;


public interface Observable {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
