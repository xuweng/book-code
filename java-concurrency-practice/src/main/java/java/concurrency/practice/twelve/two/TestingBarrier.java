package java.concurrency.practice.twelve.two;


import com.oracle.jrockit.jfr.Producer;

import java.util.function.Consumer;

/**
 * Testing with a barrier-based timer.
 */
public class TestingBarrier {
    public void test() {
        try {
            timer.clear();
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new Producer());
                pool.execute(new Consumer());
            }
            barrier.await();
            barrier.await();
            long nsPerItem = timer.getTime() / (nPairs
                    *
                    (long) nTrials);
            System.out.print("Throughput: " + nsPerItem + " ns/item");
            assertEquals(putSum.get(), takeSum.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Driver program for TimedPutTakeTest
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int tpt = 100000; // trials per thread
        for (int cap = 1; cap <= 1000; cap
                * = 10) {
            System.out.println("Capacity: " + cap);
            for (int pairs = 1; pairs <= 128; pairs
                    * = 2) {
                TimedPutTakeTest t =
                        new TimedPutTakeTest(cap, pairs, tpt);
                System.out.print("Pairs: " + pairs + "\t");
                t.test();
                System.out.print("\t");
                Thread.sleep(1000);
                t.test();
                System.out.println();
                Thread.sleep(1000);
            }
        }
        pool.shutdown();
    }
}
