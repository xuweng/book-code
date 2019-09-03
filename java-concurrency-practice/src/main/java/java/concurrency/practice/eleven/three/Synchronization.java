package java.concurrency.practice.eleven.three;

/**
 * Synchronization that has no effect. Don’t do this.
 */
public class Synchronization {
    public void synchronized1() {
        synchronized (new Object()) {
// do something
        }
    }
}
