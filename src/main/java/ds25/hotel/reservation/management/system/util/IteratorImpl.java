package ds25.hotel.reservation.management.system.util;

import ds25.hotel.reservation.management.system.pattern.iterator.Iterator;

public class IteratorImpl implements Iterator {
    private AggregateImpl aggregate;
    private int index;

    public IteratorImpl(AggregateImpl aggregate) {
        this.aggregate = aggregate;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        if (index < aggregate.getLength()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object next() {
        Object object = aggregate.getObjectAt(index);
        index++;
        return object;
    }
}
