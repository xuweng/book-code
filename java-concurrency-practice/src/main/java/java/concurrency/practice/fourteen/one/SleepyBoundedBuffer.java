package java.concurrency.practice.fourteen.one;

import org.junit.runner.notification.RunListener;

/**
 * Bounded buffer using crude blocking.
 *
 * @param <V>
 */
@RunListener.ThreadSafe
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
    private static final long SLEEP_GRANULARITY = 0;

    public SleepyBoundedBuffer(int size) {
        super(size);
    }

    public void put(V v) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isFull()) {
                    doPut(v);
                    return;
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }

    public V take() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isEmpty())
                    return doTake();
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }
}
