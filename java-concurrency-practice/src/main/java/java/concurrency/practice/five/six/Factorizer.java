package java.concurrency.practice.five.six;

import java.concurrency.practice.common.annotation.ThreadSafe;
import java.math.BigInteger;

/**
 * Factorizing servlet that caches results using Memoizer
 */
@ThreadSafe
public class Factorizer implements Servlet {
    private final Computable<BigInteger, BigInteger[]> c =
            new Computable<BigInteger, BigInteger[]>() {
                public BigInteger[] compute(BigInteger arg) {
                    return factor(arg);
                }

                private BigInteger[] factor(BigInteger arg) {
                }
            };
    private final Computable<BigInteger, BigInteger[]> cache
            = new Memoizer<BigInteger, BigInteger[]>(c);

    public void service(ServletRequest req,
                        ServletResponse resp) {
        try {
            BigInteger i = extractFromRequest(req);
            encodeIntoResponse(resp, cache.compute(i));
        } catch (InterruptedException e) {
            encodeError(resp, "factorization interrupted");
        }
    }
}
