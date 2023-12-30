package deque;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Performs some basic linked list tests.
 */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double> lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    /* Check if the GET method works. */
    public void getTest() {
        LinkedListDeque<String> city = new LinkedListDeque<>();
        city.addFirst("Beijing");
        city.addFirst("Shanghai");
        city.addFirst("Guangzhou");
        city.addLast("Shenzhen");
        assertEquals("Beijing", city.get(2));
        assertEquals("Shanghai", city.get(1));
        assertEquals("Shenzhen", city.get(3));
        assertEquals("Guangzhou", city.get(0));
        city.printDeque();
    }

    @Test
    /* Check if the Recursive GET method works. */
    public void getRecursiveTest() {
        LinkedListDeque<String> city = new LinkedListDeque<>();
        city.addFirst("Beijing");
        city.addFirst("Shanghai");
        city.addFirst("Guangzhou");
        city.addLast("Shenzhen");
        assertEquals("Beijing", city.getRecursive(2));
        assertEquals("Shanghai", city.getRecursive(1));
        assertEquals("Shenzhen", city.getRecursive(3));
        assertEquals("Guangzhou", city.getRecursive(0));
        city.printDeque();
    }

    @Test
    /* Check if the Recursive ITERATOR works. */
    public void iterTest() {
        LinkedListDeque<String> city = new LinkedListDeque<>();
        city.addFirst("Beijing");
        city.addFirst("Shanghai");
        city.addFirst("Guangzhou");
        city.addLast("Shenzhen");
        int cnt = 0;
        for (String x : city) {
            cnt += 1;
            System.out.println(x);
        }
        assertEquals(4, cnt);
    }

    @Test
    /* Check if the EQUAL method works. */
    public void equalTest() {
        LinkedListDeque<String> city = new LinkedListDeque<>();
        city.addFirst("Beijing");
        city.addFirst("Shanghai");
        city.addFirst("Guangzhou");
        city.addLast("Shenzhen");
        ArrayDeque<String> city2 = new ArrayDeque<>();
        city2.addFirst("Beijing");
        city2.addFirst("Shanghai");
        city2.addFirst("Guangzhou");
        city2.addLast("Shenzhen");
        assertTrue(city.equals(city2));
        assertTrue(city2.equals(city));
    }

    @Test
    /* Check if the EQUAL method works. */
    public void equalTest2() {
        LinkedListDeque<String> city = new LinkedListDeque<>();
        city.addFirst("Beijing");
        city.addFirst("Shanghai");
        city.addFirst("Guangzhou");
        city.addLast("Shenzhen");
        ArrayDeque<Integer> city2 = new ArrayDeque<>();
        city2.addFirst(1);
        city2.addFirst(2);
        city2.addFirst(3);
        city2.addLast(4);
        assertFalse(city.equals(city2));
        assertFalse(city2.equals(city));
    }

    @Test
    /* Check if the EQUAL method works. */
    public void equalTest3() {
        LinkedListDeque<String> city = new LinkedListDeque<>();
        city.addFirst("Beijing");
        city.addFirst("Shanghai");
        city.addFirst("Guangzhou");
        city.addLast("Shenzhen");
        LinkedListDeque<String> city2 = new LinkedListDeque<>();
        city2.addFirst("Beijing");
        city2.addFirst("Shanghai");
        city2.addFirst("Guangzhou");
        city2.addLast("Shenzhen");
        assertTrue(city.equals(city2));
        assertTrue(city2.equals(city));
    }

    @Test
    /* Check if the EQUAL method works. */
    public void equalTest4() {
        Deque<String> city = new ArrayDeque<>();
        city.addFirst("Beijing");
        city.addFirst("Shanghai");
        city.addFirst("Guangzhou");
        city.addLast("Shenzhen");
        Deque<String> city2 = new LinkedListDeque<>();
        city2.addFirst("Beijing");
        city2.addFirst("Shanghai");
        city2.addFirst("Guangzhou");
        city2.addLast("Shenzhen");
        assertTrue(city.equals(city2));
        assertTrue(city2.equals(city));
    }
}
