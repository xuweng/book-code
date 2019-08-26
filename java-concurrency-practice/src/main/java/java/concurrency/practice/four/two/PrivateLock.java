package java.concurrency.practice.four.two;

import java.concurrency.practice.two.three.Widget;

/**
 * Listing 4.3. Guarding state with a private lock.
 */
public class PrivateLock {
    private final Object myLock = new Object();
    @GuardedBy("myLock")
    Widget widget;

    void someMethod() {
        synchronized (myLock) {
// Access or modify the state of widget
        }
    }
}
