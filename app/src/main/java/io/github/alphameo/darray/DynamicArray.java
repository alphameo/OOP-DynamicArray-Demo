package io.github.alphameo.darray;

import java.util.Arrays;
import java.util.Objects;

public class DynamicArray<E> implements List<E> {

    public static final int DEFAULT_CAPACITY = 4;

    private Object[] entries;
    private int size;

    public DynamicArray(final int capacity) {
        this.entries = new Object[capacity];
    }

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArray(final E... elements) {
        this(calcCapacity(elements.length));
        for (final E e : elements) {
            this.addLast(e);
        }
    }

    @Override
    public void add(final int index, final E element) {
        validateAddIndex(index);

        if (size() == capacity()) {
            resizeCapacity(calcCapacity(size() + 1));
        }

        shiftElementsFwd(index, 1);

        entries[index] = element;
        size++;
    }

    @Override
    public boolean addAll(final int index, final List<E> c) {
        validateAddIndex(index);

        size += c.size();

        if (size() >= capacity()) {
            resizeCapacity(calcCapacity(size()));
        }

        shiftElementsFwd(index, c.size());

        for (int i = 0; i < c.size(); i++) {
            entries[index + i] = c.get(i);
        }

        return true;
    }

    @Override
    public void addFirst(final E e) {
        add(0, e);
    }

    @Override
    public void addLast(final E e) {
        this.add(this.size(), e);
    }

    @Override
    public void clear() {
        this.entries = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean contains(final Object o) {
        return this.indexOf(o) >= 0;
    }

    @Override
    public boolean containsAll(final List<E> c) {
        for (int i = 0; i < c.size(); i++) {
            if (!this.contains(c.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DynamicArray<E> other = (DynamicArray<E>) obj;

        if (this.size() != other.size()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (!entries[i].equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(final int index) {
        validateDataIndex(index);
        return (E) this.entries[index];
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
        result = prime * result + Arrays.deepHashCode(entries);
        result = prime * result + Objects.hash(size);

        return result;
    }

    @Override
    public int indexOf(final Object o) {
        for (int i = 0; i < size(); i++) {
            if (entries[i].equals(o)) {
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
    public int lastIndexOf(final Object o) {
        for (int i = size() - 1; i >= 0; i--) {
            if (entries[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean remove(final Object o) {
        for (int i = 0; i < size(); i++) {
            if (entries[i].equals(o)) {
                this.remove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(final int index) {
        final Object tmp = this.get(index);
        shiftElementsBack(index + 1, 1);

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
        final List<E> reversed = new DynamicArray<>(this.size());
        for (int i = this.size() - 1; i >= 0; i--) {
            reversed.addLast(this.get(i));
        }

        return reversed;
    }

    @Override
    public void set(final int index, final E element) {
        validateDataIndex(index);
        entries[index] = element;
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity() {
        return entries.length;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(entries, size());
    }

    private static int calcCapacity(final int minCapacity) {
        final int pow = (int) Math.ceil(Math.log(minCapacity) / Math.log(2));
        int newCpacity = 1;
        for (int i = 0; i < pow; i++) {
            newCpacity *= 2;
        }

        if (newCpacity < DEFAULT_CAPACITY) {
            newCpacity = DEFAULT_CAPACITY;
        }

        return newCpacity;
    }

    private void resizeCapacity(final int newCpacity) {
        final Object[] newArray = new Object[newCpacity];

        for (int i = 0; i < Math.min(capacity(), newArray.length); i++) {
            newArray[i] = entries[i];
        }

        entries = newArray;
    }

    private static void validateIndex(final int index, final int maxIndex) {
        if (index < 0 || index > maxIndex) {
            throw new IllegalArgumentException("Index " + index + " out of bounds for max index " + maxIndex);
        }
    }

    private void validateAddIndex(final int index) {
        validateIndex(index, size());
    }

    private void validateDataIndex(final int index) {
        validateIndex(index, size() - 1);
    }

    private void shiftElementsFwd(final int indexFrom, final int indexOffset) {
        if (size() + indexOffset > capacity()) {
            resizeCapacity(calcCapacity(size() + indexOffset));
        }

        for (int i = size() - 1; i >= indexFrom; i--) {
            entries[i + indexOffset] = entries[i];
        }
    }

    private void shiftElementsBack(final int indexFrom, final int indexOffset) {
        if (size() * 2 < capacity()) {
            resizeCapacity(calcCapacity(size()));
        }

        for (int i = indexFrom; i < size(); i++) {
            entries[i - indexOffset] = entries[i];
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size(); i++) {
            if (this.get(i) == null) {
                sb.append("null, ");
                continue;
            }
            sb.append(this.get(i).toString());
            sb.append(", ");
        }

        if (sb.charAt(sb.length() - 1) == ' ') {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");

        return sb.toString();
    }
}
