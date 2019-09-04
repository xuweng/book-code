package java.concurrency.practice.fourteen.two;

/**
 * Using conditional notification in BoundedBuffer.put
 */
public class ConditionalNotification {
    public synchronized <V> void put(V v) throws InterruptedException {
        while (isFull())
            wait();
        boolean wasEmpty = isEmpty();
        doPut(v);
        if (wasEmpty)
            notifyAll();
    }

    private <V> void doPut(V v) {
    }

    private boolean isFull() {
    }

    private boolean isEmpty() {
    }
}
