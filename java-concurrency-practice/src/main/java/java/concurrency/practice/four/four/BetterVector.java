package java.concurrency.practice.four.four;

import java.concurrency.practice.common.annotation.ThreadSafe;
import java.util.Vector;

/**
 * Extending Vector to have a put-if-absent method.
 *
 * @param <E>
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent)
            add(x);
        return absent;
    }
}
