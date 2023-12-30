package deque;

import java.util.Comparator;

/**
 * An implement of MaxArrayDeque, extended from ArrayDeque.
 *
 * @author CoderBak
 */
public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> selfCmp;

    public MaxArrayDeque(Comparator<T> c) {
        selfCmp = c;
    }

    /**
     * Returns the maximum element in the deque as governed
     * by the previously given Comparator. If the MaxArrayDeque
     * is empty, simply return null.
     */
    public T max() {
        return max(selfCmp);
    }

    /**
     * Returns the maximum element in the deque as governed
     * by the parameter Comparator c. If the MaxArrayDeque
     * is empty, simply return null.
     */
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T ans = get(0);
        for (T x : this) {
            if (c.compare(x, ans) > 0) {
                ans = x;
            }
        }
        return ans;
    }
}
