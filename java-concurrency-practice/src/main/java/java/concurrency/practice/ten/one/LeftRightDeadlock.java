package java.concurrency.practice.ten.one;

/**
 * Simple lock-ordering deadlock. Don’t do this.
 */
public class LeftRightDeadlock {
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                doSomething();
            }
        }
    }

    private void doSomething() {
    }

    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                doSomethingElse();
            }
        }
    }

    private void doSomethingElse() {
    }
}
