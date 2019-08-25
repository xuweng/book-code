package java.concurrency.practice.one.three;

import java.concurrency.practice.common.annotation.ThreadSafe;

@ThreadSafe
public class Sequence {
    private int value;

    public synchronized int getNext() {
        return value++;
    }
}
