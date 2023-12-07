package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> stdAList = new AListNoResizing<>();
        BuggyAList<Integer> testAList = new BuggyAList<>();
        for (int i = 1; i <= 3; i += 1) {
            stdAList.addLast(i);
            testAList.addLast(i);
        }
        for (int i = 1; i <= 3; i += 1) {
            assertEquals(stdAList.getLast(), testAList.getLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> test = new BuggyAList<>();
        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                test.addLast(randVal);
                // System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int testSize = test.size();
                assertEquals(size, testSize);
                // System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                if (L.size() > 0) {
                    int current = L.getLast();
                    int currentGetLast = test.getLast();
                    assertEquals(current, currentGetLast);
                    // System.out.println("getLast(" + current + ")");
                } else {
                    // System.out.println("getLast(empty)");
                }
            } else if (operationNumber == 3) {
                if (L.size() > 0) {
                    int current = L.removeLast();
                    int currentRemoveLast = test.removeLast();
                    assertEquals(current, currentRemoveLast);
                    // System.out.println("removeLast(" + current + ")");
                } else {
                    // System.out.println("removeLast(empty)");
                }
            }
        }
    }
}
