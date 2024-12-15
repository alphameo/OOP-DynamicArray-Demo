package io.github.alphameo.darr_visualization.darr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DynamicArrayTests {

    @Test
    public void testInit1() {
        try {
            DynamicArray<Integer> arr = new DynamicArray<>();
            Assertions.assertEquals(0, arr.size());
            Assertions.assertEquals(DynamicArray.DEFAULT_CAPACITY, arr.capacity());
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void testInit2() {
        DynamicArray<Integer> arr = new DynamicArray<>(12);
        Assertions.assertEquals(0, arr.size());
        Assertions.assertEquals(12, arr.capacity());
    }

    @Test
    public void testInit3() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 2, 4, 3, 5);
        Assertions.assertEquals(5, arr.size());
        Assertions.assertEquals(8, arr.capacity());
    }
}
