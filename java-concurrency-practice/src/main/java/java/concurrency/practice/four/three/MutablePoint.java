package java.concurrency.practice.four.three;

import java.concurrency.practice.common.annotation.NotThreadSafe;

/**
 * Mutable point class similar to java.awt.Point
 */
@NotThreadSafe
public class MutablePoint {
    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
