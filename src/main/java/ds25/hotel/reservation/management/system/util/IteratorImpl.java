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
        return index < aggregate.getLength();
    }

    @Override
    public Object next() {
        Object object = aggregate.getObjectAt(index);
        index++;
        return object;
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public Object previous() {
        index--;
        Object object = aggregate.getObjectAt(index);
        return object;
    }
}
