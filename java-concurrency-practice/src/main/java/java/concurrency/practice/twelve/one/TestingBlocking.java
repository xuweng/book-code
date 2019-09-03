package java.concurrency.practice.twelve.one;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;

/**
 * Testing blocking and responsiveness to interruption.
 */
public class TestingBlocking {
    private static final long LOCKUP_DETECT_TIMEOUT = 0;

    void testTakeBlocksWhenEmpty() {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
        Thread taker = new Thread() {
            @Override
            public void run() {
                try {
                    int unused = bb.take();
                    fail(); // if we get here, it’s an error
                } catch (InterruptedException success) {
                }
            }
        };
        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(taker.isAlive());
        } catch (Exception unexpected) {
            fail();
        }
    }
}
