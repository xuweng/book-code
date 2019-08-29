package java.concurrency.practice.seven.one;

import java.math.BigInteger;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Generating a second’s worth of prime numbers.
 */
public class ASecondOfPrimes {
    List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try {
            SECONDS.sleep(1);
        } finally {
            generator.cancel();
        }
        return generator.get();
    }
}
