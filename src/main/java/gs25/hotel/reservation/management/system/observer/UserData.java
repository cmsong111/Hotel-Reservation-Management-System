package gs25.hotel.reservation.management.system.observer;

import gs25.hotel.reservation.management.system.entity.User;

import java.util.ArrayList;
import java.util.Observer;

public class UserData implements Subject{

    private ArrayList obervers;
    private User user;

    public UserData() {
        obervers = new ArrayList();
    }

    @Override
    public void registerObserver(Observer o) {
        obervers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = obervers.indexOf(o);
        if (i >= 0) {
            obervers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < obervers.size(); i++) {
            Observer observer = (Observer) obervers.get(i);
            observer.update( null, user);
        }
    }

    public void userChanged() {
        notifyObservers();
    }

    public void setUser(User user) {
        this.user = user;
        userChanged();
    }


}
