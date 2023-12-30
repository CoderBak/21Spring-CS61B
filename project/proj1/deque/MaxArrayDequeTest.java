package deque;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Comparator;


public class MaxArrayDequeTest {
    @Test
    /* Check if the Recursive ITERATOR works. */
    public void basicTest() {
        MaxArrayDeque<String> city = new MaxArrayDeque<>(new StringComparator());
        city.addFirst("Beijing");
        city.addFirst("Shanghai");
        city.addFirst("Guangzhou");
        city.addLast("Shenzhen");
        assertEquals("Shenzhen", city.max());
        System.out.println(city.max());
    }

    public static class StringComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.compareTo(b);
        }
    }
}
