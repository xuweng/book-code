package java.concurrency.practice.one.three;

import java.concurrency.practice.common.annotation.NotThreadSafe;

@NotThreadSafe
public class UnsafeSequence {
    private int value;

    /**
     * Returns a
     * unique value.
     */

    public int getNext() {
        return value++;
    }
}
