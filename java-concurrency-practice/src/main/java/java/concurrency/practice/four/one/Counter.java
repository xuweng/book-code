package java.concurrency.practice.four.one;

import java.concurrency.practice.common.annotation.ThreadSafe;

/**
 * Listing 4.1. Simple thread-safe counter using the Java monitor pattern.
 */
@ThreadSafe
public final class Counter {
    @GuardedBy("this")
    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE)
            throw new IllegalStateException("counter overflow");
        return ++value;
    }
}
