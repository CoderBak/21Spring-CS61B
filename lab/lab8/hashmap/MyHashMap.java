package hashmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Collection;
import java.util.Set;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 * <p>
 * Assumes null keys will never be inserted, and does not resize down upon remove().
 *
 * @author CoderBak
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size;
    private Set<K> setOfKey;
    private int tableSize;
    private final double maxLoad;
    // You should probably define some more!

    /**
     * Constructors
     */
    public MyHashMap() {
        size = 0;
        setOfKey = new HashSet<>();
        tableSize = 8; // The default tableSize is 8
        maxLoad = 4; // The default maxLoad is 4
        buckets = createTable(tableSize);
    }

    public MyHashMap(int initialSize) {
        size = 0;
        setOfKey = new HashSet<>();
        tableSize = initialSize;
        maxLoad = 4;
        buckets = createTable(tableSize);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad     maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        size = 0;
        setOfKey = new HashSet<>();
        tableSize = initialSize;
        this.maxLoad = maxLoad;
        buckets = createTable(tableSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        MyHashMap<K, V> bucketAST = new MyHashMapLLBuckets<>(tableSize, maxLoad);
        // The default type is LinkedList
        return bucketAST.createBucket();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] newBucket = new Collection[tableSize];
        for (int i = 0; i < tableSize; i += 1) {
            newBucket[i] = createBucket();
        }
        return newBucket;
    }

    /**
     * Removes all the mappings from this map.
     */
    public void clear() {
        size = 0;
        setOfKey = new HashSet<>();
        buckets = createTable(tableSize);
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     */
    public boolean containsKey(K key) {
        int hashCode = hashCalc(key);
        Collection<Node> currentBucket = buckets[hashCode];
        for (Node item : currentBucket) {
            if (key.equals(item.key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        int hashCode = hashCalc(key);
        Collection<Node> currentBucket = buckets[hashCode];
        for (Node item : currentBucket) {
            if (key.equals(item.key)) {
                return item.value;
            }
        }
        return null;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        if (size > maxLoad * tableSize) {
            resize();
        }
        int hashCode = hashCalc(key);
        if (containsKey(key)) {
            for (Node item : buckets[hashCode]) {
                if (key.equals(item.key)) {
                    item.value = value;
                }
            }
        } else {
            buckets[hashCode].add(createNode(key, value));
            setOfKey.add(key);
            size += 1;
        }
    }

    /**
     * Returns a Set view of the keys contained in this map.
     */
    public Set<K> keySet() {
        return setOfKey;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        int hashCode = hashCalc(key);
        for (Node item : buckets[hashCode]) {
            if (key.equals(item.key)) {
                V val = item.value;
                buckets[hashCode].remove(item);
                size -= 1;
                return val;
            }
        }
        return null;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        if (!containsKey(key)) {
            return null;
        }
        int hashCode = hashCalc(key);
        for (Node item : buckets[hashCode]) {
            if (key.equals(item.key)) {
                if (value.equals(item.value)) {
                    buckets[hashCode].remove(item);
                    size -= 1;
                    return value;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * Implements Iterable.
     */
    @Override
    public Iterator<K> iterator() {
        return setOfKey.iterator();
    }

    private int hashCalc(K key) {
        int hashCode = key.hashCode() % tableSize;
        return (hashCode + tableSize) % tableSize;
    }

    /**
     * Resize the hash table.
     */
    private void resize() {
        Collection<Node>[] oldBuckets = buckets;
        tableSize *= 2;
        buckets = createTable(tableSize);
        for (int i = 0; i < tableSize / 2; i += 1) {
            Collection<Node> currentBucket = oldBuckets[i];
            for (Node item : currentBucket) {
                int hashCode = hashCalc(item.key);
                buckets[hashCode].add(item);
            }
        }
    }
}
