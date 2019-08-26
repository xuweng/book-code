package java.concurrency.practice.four.four;

import java.concurrency.practice.common.annotation.NotThreadSafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Non-thread-safe attempt to implement put-if-absent. Don’t do this.
 *
 * @param <E>
 */
@NotThreadSafe
public class ListHelper<E> {
    public List<E> list =
            Collections.synchronizedList(new ArrayList<E>());

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent)
            list.add(x);
        return absent;
    }
}
