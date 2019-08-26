package java.concurrency.practice.three.five;

import java.concurrency.practice.common.annotation.ThreadSafe;
import java.concurrency.practice.three.four.OneValueCache;
import java.math.BigInteger;

/**
 * Caching the last result using a volatile reference to an immutable
 * holder object.
 */
@ThreadSafe
public class VolatileCachedFactorizer implements Servlet {
    private volatile OneValueCache cache =
            new OneValueCache(null, null);

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = factor(i);
            cache = new OneValueCache(i, factors);
        }
        encodeIntoResponse(resp, factors);
    }
}
