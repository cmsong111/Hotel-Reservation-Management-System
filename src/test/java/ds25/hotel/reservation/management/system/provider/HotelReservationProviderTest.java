package ds25.hotel.reservation.management.system.provider;


import ds25.hotel.reservation.management.system.pattern.observer.Observable;
import ds25.hotel.reservation.management.system.pattern.observer.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HotelReservationProviderTest {

    private HotelReservationProvider provider;
    private ObserverImpl observer1;
    private ObserverImpl observer2;

    @BeforeEach
    void setUp() {
        provider = new HotelReservationProvider();
        observer1 = new ObserverImpl();
        observer2 = new ObserverImpl();
    }

    @Test
    void registerObserver() {
        provider.registerObserver(observer1);
        provider.registerObserver(observer2);

        ArrayList<Observer> observers = provider.getObervers();
        assertEquals(2, observers.size());
        assertTrue(observers.contains(observer1));
        assertTrue(observers.contains(observer2));
    }

    @Test
    void removeObserver() {
        provider.registerObserver(observer1);
        provider.registerObserver(observer2);

        provider.removeObserver(observer1);

        ArrayList<Observer> observers = provider.getObervers();
        assertEquals(1, observers.size());
        assertFalse(observers.contains(observer1));
        assertTrue(observers.contains(observer2));
    }

    @Test
    void notifyObservers() {
        provider.registerObserver(observer1);
        provider.registerObserver(observer2);

        observer1.resetUpdateCount();
        observer2.resetUpdateCount();

        provider.notifyObservers();

        assertEquals(1, observer1.getUpdateCount());
        assertEquals(1, observer2.getUpdateCount());
    }


    class ObserverImpl implements Observer {
        private int updateCount;

        @Override
        public void update(Observable o, Object arg) {
            updateCount++;
        }

        public int getUpdateCount() {
            return updateCount;
        }

        public void resetUpdateCount() {
            updateCount = 0;
        }
    }
}
