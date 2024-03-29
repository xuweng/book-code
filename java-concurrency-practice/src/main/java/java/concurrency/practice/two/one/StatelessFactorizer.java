package java.concurrency.practice.two.one;

import java.concurrency.practice.common.annotation.ThreadSafe;
import java.math.BigInteger;

@ThreadSafe
public class StatelessFactorizer implements Servlet {
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(resp, factors);
    }
}
