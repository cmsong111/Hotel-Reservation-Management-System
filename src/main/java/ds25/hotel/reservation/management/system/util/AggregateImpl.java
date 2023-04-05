package ds25.hotel.reservation.management.system.util;

import ds25.hotel.reservation.management.system.pattern.iterator.Aggregate;

public class AggregateImpl implements Aggregate {

    private Object[] objects;
    private int last = 0;

    public AggregateImpl(int size) {
        this.objects = new Object[size];
    }

    public Object getObjectAt(int index) {
        return objects[index];
    }

    public void appendObject(Object object) {
        this.objects[last] = object;
        last++;
    }

    public int getLength() {
        return last;
    }

    @Override
    public IteratorImpl iterator() {
        return new IteratorImpl(this);
    }


}
