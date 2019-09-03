package java.concurrency.practice.twelve.one;

import static junit.framework.TestCase.assertTrue;

public class TestingResource {
    private static final int CAPACITY = 0;
    private static final int THRESHOLD = 0;

    class Big {
        double[] data = new double[100000];
    }

    void testLeak() throws InterruptedException {
        BoundedBuffer<Big> bb = new BoundedBuffer<Big>(CAPACITY);
        int heapSize1 = / *snapshot heap
* /;
        for (int i = 0; i < CAPACITY; i++)
            bb.put(new Big());
        for (int i = 0; i < CAPACITY; i++)
            bb.take();
        int heapSize2 = / *snapshot heap
* /;
        assertTrue(Math.abs(heapSize1 - heapSize2) < THRESHOLD);
    }
}
