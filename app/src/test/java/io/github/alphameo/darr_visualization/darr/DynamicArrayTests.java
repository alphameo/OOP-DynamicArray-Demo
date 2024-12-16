package io.github.alphameo.darr_visualization.darr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

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

    @Test
    public void testAdd() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        arr.add(0, 1);
        arr.add(0, 12);
        arr.add(1, 3);
        arr.add(2, 5);
        arr.add(4, 9);

        assertEquals(new DynamicArray<>(12, 3, 5, 1, 9), arr);
    }

    @Test
    public void testAddAll() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 2);
        DynamicArray<Integer> addArr = new DynamicArray<>(12, 3, 5, 1);
        arr.addAll(1, addArr);

        assertEquals(new DynamicArray<>(1, 12, 3, 5, 1, 2), arr);
    }

    @Test
    public void testAddFirst() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        arr.addFirst(1);
        arr.addFirst(12);
        arr.addFirst(3);
        arr.addFirst(5);
        arr.addFirst(9);

        assertEquals(new DynamicArray<>(9, 5, 3, 12, 1), arr);
    }

    @Test
    public void testAddLast() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        arr.addLast(1);
        arr.addLast(12);
        arr.addLast(3);
        arr.addLast(5);
        arr.addLast(9);

        assertEquals(new DynamicArray<>(1, 12, 3, 5, 9), arr);
    }

    @Test
    public void testClear() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 2, 4, 5);
        arr.clear();

        assertEquals(new DynamicArray<>(), arr);
    }

    @Test
    public void testContains() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 7);

        Assertions.assertTrue(arr.contains(5));
    }

    @Test
    public void testNotContains() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 7);

        Assertions.assertTrue(!arr.contains(4));
    }

    @Test
    public void testContainsAll() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 7);
        DynamicArray<Integer> fArr = new DynamicArray<>(5, 7, 1);

        Assertions.assertTrue(arr.containsAll(fArr));
    }

    @Test
    public void testNotContainsAll() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 7);
        DynamicArray<Integer> fArr = new DynamicArray<>(1, 5, 7, 4);

        Assertions.assertTrue(!arr.containsAll(fArr));
    }

    @Test
    public void testEquals() {
        DynamicArray<Integer> arr1 = new DynamicArray<>(1, 3, 5, 6, 7);
        DynamicArray<Integer> arr2 = new DynamicArray<>(1, 3, 5, 6, 7);

        Assertions.assertTrue(arr1.equals(arr2));
    }

    @Test
    public void testNotEquals() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 7);
        DynamicArray<Integer> arr1 = new DynamicArray<>(1, 3, 6, 5, 7);
        DynamicArray<Integer> arr2 = new DynamicArray<>(1, 3, 6, 6, 7);
        DynamicArray<Integer> arr3 = new DynamicArray<>(1, 3, 5, 6);

        Assertions.assertTrue(!arr.equals(arr1));
        Assertions.assertTrue(!arr.equals(arr2));
        Assertions.assertTrue(!arr.equals(arr3));
    }

    @Test
    public void testGet() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 7);

        Assertions.assertEquals(3, arr.get(1));
    }

    @Test
    public void testGetException() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 7);

        try {
            arr.get(8);
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void testGetFirst() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 7);

        Assertions.assertEquals(1, arr.getFirst());
    }

    @Test
    public void testGetLast() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 7);

        Assertions.assertEquals(7, arr.getLast());
    }

    @Test
    public void testIndexOf() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 5);

        Assertions.assertEquals(2, arr.indexOf(5));
    }

    @Test
    public void testIndexOfOut() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 5);

        Assertions.assertEquals(-1, arr.indexOf(44));
    }

    @Test
    public void testEmpty() {
        DynamicArray<Integer> arr = new DynamicArray<>();

        Assertions.assertTrue(arr.isEmpty());
    }

    @Test
    public void testNtoEmpty() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 5);

        Assertions.assertTrue(!arr.isEmpty());
    }

    @Test
    public void testLastIndexOf() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 5);

        Assertions.assertEquals(4, arr.lastIndexOf(5));
    }

    @Test
    public void testLastIndexOfOut() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 5);

        Assertions.assertEquals(-1, arr.lastIndexOf(9));
    }

    @Test
    public void testRemoveObj() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 5);

        Assertions.assertTrue(arr.remove((Integer) 5));
        Assertions.assertEquals(new DynamicArray<>(1, 3, 6, 5), arr);
    }

    @Test
    public void testRemoveObjOut() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 5);

        Assertions.assertTrue(!arr.remove((Integer) 12));
        Assertions.assertEquals(new DynamicArray<>(1, 3, 5, 6, 5), arr);
    }

    @Test
    public void testRemoveIndex() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 5);

        Assertions.assertEquals(6, arr.remove(3));
        Assertions.assertEquals(new DynamicArray<>(1, 3, 5, 5), arr);
    }

    @Test
    public void testRemoveFirst() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 5);

        Assertions.assertEquals(1, arr.removeFirst());
        Assertions.assertEquals(new DynamicArray<>(3, 5, 6, 5), arr);
    }

    @Test
    public void testRemoveLast() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 5);

        Assertions.assertEquals(5, arr.removeLast());
        Assertions.assertEquals(new DynamicArray<>(1, 3, 5, 6), arr);
    }

    @Test
    public void testReversed() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 5);

        Assertions.assertEquals(new DynamicArray<>(5, 6, 5, 3, 1), arr.reversed());
    }

    @Test
    public void testSet() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 5);
        arr.set(3, 44);
        Assertions.assertEquals(new DynamicArray<>(1, 3, 5, 44, 5), arr);
    }

    @Test
    public void testSetException() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 5);
        try {
            arr.set(8, 44);
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void testSize() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 5);
        Assertions.assertEquals(5, arr.size());
    }

    @Test
    public void testToArray() {
        DynamicArray<Integer> arr = new DynamicArray<>(1, 3, 5, 6, 5);
        Assertions.assertTrue(Arrays.equals(new Integer[] { 1, 3, 5, 6, 5 }, arr.toArray()));
    }
}
