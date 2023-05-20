package ds25.hotel.reservation.management.system.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class IteratorImplTest {
    private AggregateImpl aggregate;

    @BeforeEach
    public void setup() {
        aggregate = new AggregateImpl(3);
        aggregate.appendObject("Object 1");
        aggregate.appendObject("Object 2");
        aggregate.appendObject("Object 3");
    }

    @Test
    void testGetObjectAt1() {
        Object object = aggregate.getObjectAt(1);
        Assertions.assertEquals("Object 2", object);
    }

    @Test
    void testGetObjectAt2() {
        Object object = aggregate.getObjectAt(2);
        Assertions.assertEquals("Object 3", object);
    }

    @Test
    void testHasNext1() {
        Object object = aggregate.getObjectAt(1);
        Assertions.assertEquals("Object 2", object);
        Assertions.assertTrue(aggregate.iterator().hasNext());
    }

    @Test
    void testPrev2() {
        Object object = aggregate.getObjectAt(0);
        Assertions.assertEquals("Object 1", object);
        Assertions.assertFalse(aggregate.iterator().hasPrevious());

    }

    @Test
    void testPrev1() {
        Object object = aggregate.getObjectAt(0);
        Assertions.assertEquals("Object 1", object);
        Assertions.assertFalse(aggregate.iterator().hasPrevious());
    }

    @Test
    void testNext2() {
        Object object = aggregate.getObjectAt(1);
        Assertions.assertEquals("Object 2", object);
        Assertions.assertTrue(aggregate.iterator().hasNext());
    }
}

