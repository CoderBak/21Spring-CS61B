package deque;

import java.util.Iterator;

/**
 * An implement of ArrayDeque
 *
 * @author CoderBak
 */

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size, contained, start, end;

    /**
     * Initialize the ArrayDeque.
     * Notice: the items is a circular array.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 8;
        contained = 0;
        start = 0;
        end = 1;
        // start refers to the start of the seq, when adding object.
        // end refers to the end of the seq, when adding object.
    }

    /**
     * Resizing.
     */
    private void sizeExpand() {
        T[] newArray = (T[]) new Object[2 * size];
        if (start == size - 1) {
            System.arraycopy(items, 0, newArray, 0, size);
        } else {
            System.arraycopy(items, start + 1, newArray, 0, size - start - 1);
            System.arraycopy(items, 0, newArray, size - start - 1, start + 1);
        }
        start = 2 * size - 1;
        end = size;
        size *= 2;
        items = newArray;
    }

    private void sizeShrink() {
        T[] newArray = (T[]) new Object[size / 2];
        if (start < end || end == 0 || start == size - 1) {
            System.arraycopy(items, (start + 1) % size, newArray, 0, contained);
        } else {
            System.arraycopy(items, start + 1, newArray, 0, size - start - 1);
            System.arraycopy(items, 0, newArray, size - start - 1, end);
        }
        start = size / 2 - 1;
        end = contained;
        size /= 2;
        items = newArray;
    }

    /**
     * Adds an item of type T to the front of the deque.
     * ITEM is never null.
     */
    @Override
    public void addFirst(T item) {
        if (contained == size) {
            sizeExpand();
        }
        items[start] = item;
        start = (start + size - 1) % size;
        contained += 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * ITEM is never null.
     */
    @Override
    public void addLast(T item) {
        if (contained == size) {
            sizeExpand();
        }
        items[end] = item;
        end = (end + 1) % size;
        contained += 1;
    }

    /**
     * Returns the number of items in the deque.
     */
    @Override
    public int size() {
        return contained;
    }

    /**
     * Prints the items in the deque from first to last,
     * separated by a space. Once all the items have been
     * printed, print out a new line.
     */
    @Override
    public void printDeque() {
        int pos = (start + 1) % size;
        for (int i = 1; i <= contained; i += 1) {
            System.out.print(items[pos]);
            System.out.print(" ");
            pos = (pos + 1) % size;
        }
        System.out.print("\n");
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (contained == 0) {
            return null;
        }
        if (contained * 4 == size) {
            sizeShrink();
        }
        start = (start + 1) % size;
        contained -= 1;
        return items[start];
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (contained == 0) {
            return null;
        }
        if (contained * 4 == size) {
            sizeShrink();
        }
        end = (end - 1 + size) % size;
        contained -= 1;
        return items[end];
    }

    /**
     * Gets the item at the given index, where 0 is the front.
     * If no such item exists, returns null.
     */
    @Override
    public T get(int index) {
        if (index >= contained) {
            return null;
        }
        int pos = (start + 1) % size;
        for (int i = 1; i <= index; i += 1) {
            pos = (pos + 1) % size;
        }
        return items[pos];
    }

    /**
     * Returns an iterator to iterate through the deque.
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    /**
     * Returns whether the parameter o is equal to deque.
     * It is really a slow way, but using Iterators cause
     * mysterious error in two points in the AutoGrader.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<?> other = (Deque<?>) o;
        if (other.size() != size()) {
            return false;
        }
        for (int i = 0; i < size(); i += 1) {
            if (!get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Implements the ArrayDeque Iterator.
     *
     * @author CoderBak
     */
    private class ArrayDequeIterator implements Iterator<T> {
        private int cnt;
        private int pos;

        ArrayDequeIterator() {
            cnt = 0;
            pos = (start + 1) % size;
        }

        @Override
        public boolean hasNext() {
            return cnt < contained;
        }

        @Override
        public T next() {
            T current = items[pos];
            pos = (pos + 1) % size;
            cnt += 1;
            return current;
        }
    }
}
