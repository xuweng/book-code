package java.concurrency.practice.four.four;

import java.concurrency.practice.common.annotation.ThreadSafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementing put-if-absent with client-side locking.
 *
 * @param <E>
 */
@ThreadSafe
public class ListHelper2<E> {
    public List<E> list =
            Collections.synchronizedList(new ArrayList<E>());

    public boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent)
                list.add(x);
            return absent;
        }
    }
}
