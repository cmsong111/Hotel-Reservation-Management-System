package ds25.hotel.reservation.management.system.pattern.strategy;

import ds25.hotel.reservation.management.system.dto.hotel.HotelDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SortStrategyTest {

    ArrayList<HotelDto> arrayList;
    SortStrategy sortingAlgorithm;

    @BeforeEach
    void setup() {
        arrayList = new ArrayList<>();
        arrayList.add(HotelDto.builder().idx(1L).name("D").build());
        arrayList.add(HotelDto.builder().idx(5L).name("A").build());
        arrayList.add(HotelDto.builder().idx(3L).name("B").build());
        arrayList.add(HotelDto.builder().idx(4L).name("E").build());
        arrayList.add(HotelDto.builder().idx(2L).name("C").build());

        sortingAlgorithm = new BuildAscSort();
    }


    @Test
    void nameAcsSorting() {
        sortingAlgorithm = new NameAscSort();
        sortingAlgorithm.sorting(arrayList);

        assertEquals("A", arrayList.get(0).getName());
        assertEquals("B", arrayList.get(1).getName());
        assertEquals("C", arrayList.get(2).getName());
        assertEquals("D", arrayList.get(3).getName());
        assertEquals("E", arrayList.get(4).getName());


    }

    @Test
    void nameDescSorting() {
        sortingAlgorithm = new NameDescSort();
        sortingAlgorithm.sorting(arrayList);

        assertEquals("E", arrayList.get(0).getName());
        assertEquals("D", arrayList.get(1).getName());
        assertEquals("C", arrayList.get(2).getName());
        assertEquals("B", arrayList.get(3).getName());
        assertEquals("A", arrayList.get(4).getName());
    }

    @Test
    void buildAscSorting() {
        sortingAlgorithm = new BuildAscSort();
        sortingAlgorithm.sorting(arrayList);

        assertEquals(1L, arrayList.get(0).getIdx());
        assertEquals(2L, arrayList.get(1).getIdx());
        assertEquals(3L, arrayList.get(2).getIdx());
        assertEquals(4L, arrayList.get(3).getIdx());
        assertEquals(5L, arrayList.get(4).getIdx());
    }

    @Test
    void buildDescSorting() {
        sortingAlgorithm = new BuildDescSort();
        sortingAlgorithm.sorting(arrayList);

        assertEquals(5L, arrayList.get(0).getIdx());
        assertEquals(4L, arrayList.get(1).getIdx());
        assertEquals(3L, arrayList.get(2).getIdx());
        assertEquals(2L, arrayList.get(3).getIdx());
        assertEquals(1L, arrayList.get(4).getIdx());
    }

}
