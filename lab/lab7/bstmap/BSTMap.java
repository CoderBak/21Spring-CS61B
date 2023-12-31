package bstmap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implements the ADT Map using BST.
 *
 * @author CoderBak
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private int size;
    private Node root, sentinel; // Avoiding empty tree.

    private ArrayList<K> nodeList;

    public BSTMap() {
        size = 0;
        root = null;
        sentinel = new Node(null, null, null, null, null);
    }

    /**
     * Removes all the mappings from this map.
     */
    @Override
    public void clear() {
        size = 0;
        root = null;
        sentinel = new Node(null, null, null, null, null);
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     */
    @Override
    public boolean containsKey(K key) {
        return (containsHelper(root, key) != null);
    }

    // The reason why using helper is that the value itself might be null.
    private Node containsHelper(Node cur, K key) {
        if (cur == null) {
            return null;
        }
        int cmp = key.compareTo(cur.key);
        if (cmp < 0) {
            return containsHelper(cur.left, key);
        } else if (cmp > 0) {
            return containsHelper(cur.right, key);
        } else {
            return cur;
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or
     * null if this map contains no mapping for the key.
     * Notice: if the value itself is null, still return null.
     */
    @Override
    public V get(K key) {
        return getHelper(root, key);
    }

    private V getHelper(Node cur, K key) {
        if (cur == null) {
            return null;
        }
        int cmp = key.compareTo(cur.key);
        if (cmp < 0) {
            return getHelper(cur.left, key);
        } else if (cmp > 0) {
            return getHelper(cur.right, key);
        } else {
            return cur.value;
        }
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(sentinel, root, key, value);
    }

    private Node putHelper(Node parent, Node cur, K key, V value) {
        // Add something after the currentNode, and return the new one.
        if (cur == null) {
            size += 1;
            return new Node(parent, null, null, key, value);
        }
        int cmp = key.compareTo(cur.key);
        if (cmp < 0) {
            cur.left = putHelper(cur, cur.left, key, value);
        } else if (cmp > 0) {
            cur.right = putHelper(cur, cur.right, key, value);
        } else {
            cur.value = value;
        }
        return cur;
    }

    /**
     * Prints all the objects in BSTMap, mainly used for debugging.
     */
    public void printInOrder() {
        printHelper(root);
    }

    private void printHelper(Node cur) {
        if (cur == null) {
            return;
        }
        printHelper(cur.left);
        System.out.println(cur.key);
        printHelper(cur.right);
    }

    /**
     * Returns a Set view of the keys contained in this map.
     */
    @Override
    public Set<K> keySet() {
        Iterator<K> iter = iterator();
        Set<K> curSet = new HashSet<>();
        while (iter.hasNext()) {
            curSet.add(iter.next());
        }
        return curSet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     */
    @Override
    public V remove(K key) {
        return removeHelper(key, null, 0);
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value.
     */
    @Override
    public V remove(K key, V value) {
        return removeHelper(key, value, 1);
    }

    /**
     * op = 1 : remove needs equal value.
     * op = 0 : remove only needs equal key.
     */

    private V removeHelper(K key, V value, int op) {
        Node current = containsHelper(root, key);
        if (current == null) {
            return null;
        }
        V val = current.value;
        if (op == 1 && val != value) {
            return null;
        }
        root = removeHelper2(sentinel, root, key);
        size -= 1;
        return val;
    }

    private Node removeHelper2(Node parent, Node cur, K key) {
        if (cur == null) {
            return null;
        }
        int cmp = key.compareTo(cur.key);
        if (cmp < 0) {
            cur.left = removeHelper2(cur, cur.left, key);
        } else if (cmp > 0) {
            cur.right = removeHelper2(cur, cur.right, key);
        } else {
            switch (cur.childCount()) {
                case 0:
                    return null;
                case 1:
                    return cur.child();
                default:
                    swap(getSuccessor(cur), cur);
                    cur.right = removeHelper2(cur, cur.right, key);
            }
        }
        cur.parent = parent;
        return cur;
    }

    private void swap(Node a, Node b) {
        K tmpK = a.key;
        V tmpV = a.value;
        a.key = b.key;
        a.value = b.value;
        b.key = tmpK;
        b.value = tmpV;
    }

    private Node getSuccessor(Node start) {
        return getSuccessorHelper(start.right);
    }

    private Node getSuccessorHelper(Node cur) {
        if (cur.left == null) {
            return cur;
        }
        return getSuccessorHelper(cur.left);
    }

    @Override
    public Iterator<K> iterator() {
        nodeList = new ArrayList<>();
        nodeListBuilder(root);
        return nodeList.iterator();
    }

    private void nodeListBuilder(Node cur) {
        if (cur == null) {
            return;
        }
        nodeListBuilder(cur.left);
        nodeList.add(cur.key);
        nodeListBuilder(cur.right);
    }

    private class Node {
        private K key;

        private V value;
        private Node left, right, parent;

        Node(Node parent, Node left, Node right, K key, V value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.key = key;
            this.value = value;
        }

        public int childCount() {
            int cnt = 0;
            if (left == null) {
                cnt += 1;
            }
            if (right == null) {
                cnt += 1;
            }
            return (2 - cnt);
        }

        /**
         * Returns a non-null child for this.
         * Won't work for leaf.
         */
        public Node child() {
            if (left != null) {
                return left;
            }
            return right;
        }
    }
}