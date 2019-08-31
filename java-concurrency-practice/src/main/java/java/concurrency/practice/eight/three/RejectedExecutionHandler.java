package java.concurrency.practice.eight.three;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Creating a fixed-sized thread pool with a bounded queue and the
 * caller-runs saturation policy.
 */
public class RejectedExecutionHandler {
    private static final int N_THREADS = 1;
    private static final int CAPACITY = 1;

    public void setRejectedExecutionHandler() {
        ThreadPoolExecutor executor
                = new ThreadPoolExecutor(N_THREADS, N_THREADS,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(CAPACITY));
        executor.setRejectedExecutionHandler(
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
