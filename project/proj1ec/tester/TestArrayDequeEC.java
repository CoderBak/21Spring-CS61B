package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void RandomTest1() {
        StudentArrayDeque<Integer> test = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> std = new ArrayDequeSolution<>();
        int testSize = 100;
        int bound = 100;
        StringBuilder message = new StringBuilder();
        for(int i = 1; i <= testSize; i += 1) {
            int element = StdRandom.uniform(bound * (-1), bound);
            test.addFirst(element);
            std.addFirst(element);
            message.append("addFirst(").append(element).append(")\n");
        }
        for(int i = 1; i <= testSize; i += 1) {
            double operator = StdRandom.uniform(0.0, 1.0);
            if (operator < 0.5) {
                message.append("removeFirst()\n");
                assertEquals(String.valueOf(message), std.removeFirst(), test.removeFirst());
            } else {
                message.append("removeLast()\n");
                assertEquals(String.valueOf(message), std.removeLast(), test.removeLast());
            }
        }
    }
}
