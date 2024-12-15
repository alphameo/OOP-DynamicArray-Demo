package io.github.alphameo.darr_visualization.darr;

import java.util.Arrays;
import java.util.Objects;

public class DynamicArray<E> implements List<E> {

    public static final int DEFAULT_CAPACITY = 4;

    private Object[] array;
    int size;

    public DynamicArray(int capacity) {
        this.array = new Object[capacity];
    }

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArray(E... elements) {
        this(calcCapacity(elements.length));
        for (E e : elements) {
            this.addLast(e);
        }
    }

    @Override
    public void add(int index, E element) {
        validateAddIndex(index);

        if (size() == capacity()) {
            resizeCapacity(calcCapacity(size() + 1));
        }

        shiftElementsFwd(index, index + 1);

        array[index] = element;
        size++;
    }

    @Override
    public boolean addAll(int index, List<E> c) {
        validateAddIndex(index);

        int targetSize = size() + c.size();

        if (targetSize > capacity()) {
            resizeCapacity(calcCapacity(targetSize));
        }

        shiftElementsFwd(index, index + c.size());

        for (int i = index; i < index + c.size(); i++) {
            array[i] = c.get(i);
        }

        size = targetSize;

        return true;
    }

    @Override
    public void addFirst(E e) {
        shiftElementsFwd(0, 1);
        add(0, e);
    }

    @Override
    public void addLast(E e) {
        this.add(this.size(), e);
    }

    @Override
    public void clear() {
        this.array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean contains(Object o) {
        return this.indexOf(o) >= 0;
    }

    @Override
    public boolean containsAll(List<E> c) {
        for (int i = 0; i < c.size(); i++) {
            if (!this.contains(c.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DynamicArray<E> other = (DynamicArray<E>) obj;

        return Arrays.deepEquals(array, other.array) && size == other.size;
    }

    @Override
    public E get(int index) {
        validateDataIndex(index);
        return (E) this.array[index];
    }

    @Override
    public E getFirst() {
        return this.get(0);
    }

    @Override
    public E getLast() {
        return this.get(size() - 1);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(array);
        result = prime * result + Objects.hash(size);

        return result;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size(); i++) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size() - 1; i >= 0; i--) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size(); i++) {
            if (array[i].equals(o)) {
                this.remove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public E remove(int index) {
        if (size() * 2 < capacity()) {
            resizeCapacity(calcCapacity(size()));
        }

        validateDataIndex(index);

        Object tmp = array[index];
        shiftElementsBack(index + 1, index);

        size--;
        return (E) tmp;
    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(size() - 1);
    }

    @Override
    public List<E> reversed() {
        List<E> reversed = new DynamicArray<>(this.size());
        for (int i = this.size() - 1; i >= 0; i--) {
            reversed.addLast(this.get(i));
        }

        return reversed;
    }

    @Override
    public void set(int index, E element) {
        validateDataIndex(index);
        shiftElementsFwd(index, index + 1);
        array[index] = element;
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity() {
        return array.length;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size());
    }

    private static int calcCapacity(int minCapacity) {
        int pow = (int) Math.ceil(Math.log(minCapacity) / Math.log(2));
        int newCpacity = 1;
        for (int i = 0; i <= pow; i++) {
            newCpacity *= 2;
        }

        return newCpacity;
    }

    private boolean resizeCapacity(int newCpacity) {
        boolean appliedCapacity = true;

        if (newCpacity < DEFAULT_CAPACITY) {
            appliedCapacity = false;
        }

        Object[] newArray = new Object[newCpacity];

        for (int i = 0; i < Math.min(capacity(), newArray.length); i++) {
            newArray[i] = array[i];
        }

        array = newArray;

        return appliedCapacity;
    }

    private static void validateIndex(int index, int maxIndex) {
        if (index < 0 || index > maxIndex) {
            throw new IllegalArgumentException("Index out of bounds");
        }
    }

    private void validateAddIndex(int index) {
        validateIndex(index, size());
    }

    private void validateDataIndex(int index) {
        validateIndex(index, size() - 1);
    }

    private void shiftElementsFwd(int indexFrom, int indexTo) {
        int indexOffset = indexTo - indexFrom;
        resizeCapacity(size() + indexOffset);

        for (int i = size + indexOffset; i > indexTo; i--) {
            array[i] = array[i - indexOffset];
        }
    }

    private void shiftElementsBack(int indexFrom, int indexTo) {
        int indexOffset = indexFrom - indexTo;
        resizeCapacity(size() + indexOffset);

        for (int i = indexFrom; i < size(); i++) {
            array[i] = array[i + indexOffset];
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
