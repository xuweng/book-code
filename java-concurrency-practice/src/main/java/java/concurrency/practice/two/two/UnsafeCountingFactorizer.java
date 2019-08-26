package java.concurrency.practice.two.two;

import java.concurrency.practice.common.annotation.NotThreadSafe;
import java.math.BigInteger;

@NotThreadSafe
public class UnsafeCountingFactorizer implements Servlet {
    private long count = 0;
    public long getCount() { return count; }
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        ++count;
        encodeIntoResponse(resp, factors);
    }
}