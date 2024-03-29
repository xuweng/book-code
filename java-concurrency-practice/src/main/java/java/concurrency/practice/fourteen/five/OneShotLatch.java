package java.concurrency.practice.fourteen.five;

import org.junit.runner.notification.RunListener;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Binary latch using AbstractQueuedSynchronizer
 */
@RunListener.ThreadSafe
public class OneShotLatch {
    private final Sync sync = new Sync();

    public void signal() {
        sync.releaseShared(0);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }

    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int ignored) {
// Succeed if latch is open (state == 1), else fail
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int ignored) {
            setState(1); // Latch is now open
            return true; // Other threads may now be able to acquire
        }
    }
}
