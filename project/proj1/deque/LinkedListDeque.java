package deque;

import java.util.Iterator;

/**
 * An implement of LinkedListDeque
 *
 * @author CoderBak
 */

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private int size;
    private final Node sentinel;

    /**
     * Initialize the LinkedListDeque.
     */
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /**
     * Adds an item of type T to the front of the deque.
     * ITEM is never null.
     */
    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * ITEM is never null.
     */
    @Override
    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    /**
     * Returns the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last,
     * separated by a space. Once all the items have been
     * printed, print out a new line.
     */
    @Override
    public void printDeque() {
        if (this.isEmpty()) {
            System.out.print("\n");
            return;
        }
        Node currentNode = sentinel.next;
        while (currentNode != sentinel) {
            System.out.print(currentNode.item);
            System.out.print(" ");
            currentNode = currentNode.next;
        }
        System.out.print("\n");
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T valueOfFirst = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return valueOfFirst;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T valueOfLast = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return valueOfLast;
    }

    /**
     * Gets the item at the given index, where 0 is the front.
     * If no such item exists, returns null.
     */
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node currentNode = sentinel.next;
        for (int i = 1; i <= index; i += 1) {
            currentNode = currentNode.next;
        }
        return currentNode.item;
    }

    /**
     * A get method implemented using recursion.
     */
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    /**
     * Helper of the getRecursive method.
     */
    private T getRecursiveHelper(Node current, int index) {
        if (index == 0) {
            return current.item;
        }
        return getRecursiveHelper(current.next, index - 1);
    }

    /**
     * Returns an iterator to iterate through the deque.
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
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
     * Implements the LinkedListDeque Iterator.
     *
     * @author CoderBak
     */
    private class LinkedListDequeIterator implements Iterator<T> {
        private Node current;
        private int pos;

        LinkedListDequeIterator() {
            current = sentinel;
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            pos += 1;
            current = current.next;
            return current.item;
        }
    }

    /**
     * Implements a node in LinkedList.
     *
     * @author CoderBak
     */
    private class Node {
        private final T item;
        private Node next, prev;

        /**
         * Implements the initialization of Node.
         */
        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
}
