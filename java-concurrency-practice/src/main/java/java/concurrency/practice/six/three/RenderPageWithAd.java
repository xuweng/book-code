package java.concurrency.practice.six.three;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * Fetching an advertisement with a time budget.
 */
public class RenderPageWithAd {
    private static final long TIME_BUDGET = 0;
    private static final Ad DEFAULT_AD = null;
    private CompletionService exec;

    Page renderPageWithAd() throws InterruptedException {
        long endNanos = System.nanoTime() + TIME_BUDGET;
        Future<Ad> f = exec.submit(new FetchAdTask());
// Render the page while waiting for the ad
        Page page = renderPageBody();
        Ad ad;
        try {
// Only wait for the remaining time budget
            long timeLeft = endNanos - System.nanoTime();
            ad = f.get(timeLeft, NANOSECONDS);
        } catch (ExecutionException e) {
            ad = DEFAULT_AD;
        } catch (TimeoutException e) {
            ad = DEFAULT_AD;
            f.cancel(true);
        }
        page.setAd(ad);
        return page;
    }

    private Page renderPageBody() {
    }
}
