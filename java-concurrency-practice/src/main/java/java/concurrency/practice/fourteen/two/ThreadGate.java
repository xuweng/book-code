package java.concurrency.practice.fourteen.two;

import org.junit.runner.notification.RunListener;

import java.concurrency.practice.common.annotation.GuardedBy;

/**
 * Recloseable gate using wait and notifyAll
 */
@RunListener.ThreadSafe
public class ThreadGate {
    // CONDITION-PREDICATE: opened-since(n) (isOpen || generation>n)
    @GuardedBy("this")
    private boolean isOpen;
    @GuardedBy("this")
    private int generation;

    public synchronized void close() {
        isOpen = false;
    }

    public synchronized void open() {
        ++generation;
        isOpen = true;
        notifyAll();
    }

    // BLOCKS-UNTIL: opened-since(generation on entry)
    public synchronized void await() throws InterruptedException {
        int arrivalGeneration = generation;
        while (!isOpen && arrivalGeneration == generation)
            wait();
    }
}
