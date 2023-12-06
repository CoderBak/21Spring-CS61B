package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes1() {
        IntList lst = IntList.of(1, 1, 1, 4, 4);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1 -> 1 -> 1 -> 4 -> 4", lst.toString());
        assertFalse(changed);
    }

    @Test
    public void testSquarePrimes2() {
        IntList lst = IntList.of(4, 4, 4, 4, 4);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 4 -> 4 -> 4 -> 4", lst.toString());
        assertFalse(changed);
    }

    @Test
    public void testSquarePrimes3() {
        IntList lst = IntList.of(2);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes4() {
        IntList lst = IntList.of(3);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("9", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes5() {
        IntList lst = IntList.of(1, 1, 1, 2);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1 -> 1 -> 1 -> 4", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes6() {
        IntList lst = IntList.of(2, 1, 1, 1);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 1 -> 1 -> 1", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes7() {
        IntList lst = IntList.of(1);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1", lst.toString());
        assertFalse(changed);
    }

    @Test
    public void testSquarePrimes8() {
        IntList lst = IntList.of(83);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("6889", lst.toString());
        assertTrue(changed);
    }
    @Test
    public void testSquarePrimes9() {
        IntList lst = IntList.of(-83);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("6889", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes10() {
        IntList lst = IntList.of(0, 0, 2);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("0 -> 0 -> 4", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes11() {
        IntList lst = IntList.of(0, 0, 0);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("0 -> 0 -> 0", lst.toString());
        assertFalse(changed);
    }

    @Test
    public void testSquarePrimes12() {
        IntList lst = IntList.of(1, 2, 3, 4);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1 -> 4 -> 9 -> 4", lst.toString());
        assertTrue(changed);
    }
}
