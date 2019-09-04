package java.concurrency.practice.fifteen.two;

import org.junit.runner.notification.RunListener;

/**
 * Nonblocking counter using CAS.
 */
@RunListener.ThreadSafe
public class CasCounter {
    private SimulatedCAS value;

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get();
        }
        while (v != value.compareAndSwap(v, v + 1));
        return v + 1;
    }
}
