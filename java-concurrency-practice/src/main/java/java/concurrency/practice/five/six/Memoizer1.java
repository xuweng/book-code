package java.concurrency.practice.five.six;

import java.concurrency.practice.common.annotation.GuardedBy;
import java.util.HashMap;
import java.util.Map;

/**
 * Initial cache attempt using HashMap and synchronization.
 *
 * @param <A>
 * @param <V>
 */
public class Memoizer1<A, V> implements Computable<A, V> {
    @GuardedBy("this")
    private final Map<A, V> cache = new HashMap<A, V>();
    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
