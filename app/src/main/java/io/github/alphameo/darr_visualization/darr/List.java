package io.github.alphameo.darr_visualization.darr;

public interface List<E> {

    void add(int index, E element);

    boolean addAll(int index, List<E> c);

    void addFirst(E e);

    void addLast(E e);

    void clear();

    boolean contains(Object o);

    boolean containsAll(List<E> c);

    boolean equals(Object o);

    E get(int index);

    E getFirst();

    E getLast();

    int hashCode();

    int indexOf(Object o);

    boolean isEmpty();

    int lastIndexOf(Object o);

    E remove(int index);

    boolean remove(Object o);

    E removeFirst();

    E removeLast();

    List<E> reversed();

    void set(int index, E element);

    int size();

    Object[] toArray();
}
