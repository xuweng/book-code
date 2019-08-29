package java.concurrency.practice.seven.one;

import java.concurrency.practice.common.annotation.GuardedBy;
import java.concurrency.practice.common.annotation.ThreadSafe;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Using a volatile field to hold cancellation state.
 */
@ThreadSafe
public class PrimeGenerator implements Runnable {
    @GuardedBy("this")
    private final List<BigInteger> primes
            = new ArrayList<BigInteger>();
    private volatile boolean cancelled;

    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<BigInteger>(primes);
    }
}
