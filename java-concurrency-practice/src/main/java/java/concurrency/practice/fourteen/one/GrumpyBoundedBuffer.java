package java.concurrency.practice.fourteen.one;

import org.junit.runner.notification.RunListener;

/**
 * Bounded buffer that balks when preconditions are not met.
 *
 * @param <V>
 */
@RunListener.ThreadSafe
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
    public GrumpyBoundedBuffer(int size) {
        super(size);
    }

    public synchronized void put(V v) throws BufferFullException {
        if (isFull())
            throw new BufferFullException();
        doPut(v);
    }

    public synchronized V take() throws BufferEmptyException {
        if (isEmpty())
            throw new BufferEmptyException();
        return doTake();
    }
}
