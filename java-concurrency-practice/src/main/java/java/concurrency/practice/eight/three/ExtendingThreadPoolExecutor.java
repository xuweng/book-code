package java.concurrency.practice.eight.three;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Modifying an Executor created with the standard factories.
 */
public class ExtendingThreadPoolExecutor {
    public void setCorePoolSize() {
        ExecutorService exec = Executors.newCachedThreadPool();
        if (exec instanceof ThreadPoolExecutor)
            ((ThreadPoolExecutor) exec).setCorePoolSize(10);
        else
            throw new AssertionError("Oops, bad assumption");
    }
}
