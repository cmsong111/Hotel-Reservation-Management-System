<<<<<<< HEAD:src/main/java/ds25/hotel/reservation/management/system/observer/Observable.java
package ds25.hotel.reservation.management.system.observer;
=======
package gs25.hotel.reservation.management.system.pattern.observer;
>>>>>>> main:src/main/java/ds25/hotel/reservation/management/system/pattern/observer/Observable.java


public interface Observable {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
