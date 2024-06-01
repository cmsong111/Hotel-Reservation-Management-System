package ds25.hotel.reservation.management.system.util.iterator;

public interface Iterator<T> {
    public abstract boolean hasNext();

    public abstract T next();

    public abstract boolean hasPrevious();

    public abstract T previous();
}
