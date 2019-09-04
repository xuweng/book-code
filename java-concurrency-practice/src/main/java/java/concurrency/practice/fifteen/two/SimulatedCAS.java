package java.concurrency.practice.fifteen.two;

import org.junit.runner.notification.RunListener;

import java.concurrency.practice.common.annotation.GuardedBy;

/**
 * Simulated CAS operation.
 */
@RunListener.ThreadSafe
public class SimulatedCAS {
    @GuardedBy("this")
    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue,
                                           int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue)
            value = newValue;
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue,
                                              int newValue) {
        return (expectedValue
                == compareAndSwap(expectedValue, newValue));
    }
}
