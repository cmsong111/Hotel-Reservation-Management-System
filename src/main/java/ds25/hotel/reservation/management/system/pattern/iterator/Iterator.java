package ds25.hotel.reservation.management.system.pattern.iterator;

public interface Iterator {
    public abstract boolean hasNext();

    public abstract Object next();

    public abstract boolean hasPrevious();

    public abstract Object previous();
}
