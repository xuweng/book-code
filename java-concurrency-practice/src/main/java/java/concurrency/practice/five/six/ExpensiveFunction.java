package java.concurrency.practice.five.six;

import java.math.BigInteger;


public class ExpensiveFunction
        implements Computable<String, BigInteger> {
    public BigInteger compute(String arg) {
// after deep thought...
        return new BigInteger(arg);
    }
}
