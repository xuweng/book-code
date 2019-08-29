package java.concurrency.practice.seven.one;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * Unreliable cancellation that can leave producers stuck in a blocking
 * operation. Don’t do this.
 */
class BrokenPrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    private BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!cancelled)
                queue.put(p = p.nextProbablePrime());
        } catch (InterruptedException consumed) {
        }
    }

    public void cancel() {
        cancelled = true;
    }

    void consumePrimes() throws InterruptedException {
        BlockingQueue<BigInteger> primes = null;
        BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
        producer.start();
        try {
            while (needMorePrimes())
                consume(primes.take());
        } finally {
            producer.cancel();
        }
    }

    private void consume(BigInteger take) {
    }

    private boolean needMorePrimes() {
    }
}

