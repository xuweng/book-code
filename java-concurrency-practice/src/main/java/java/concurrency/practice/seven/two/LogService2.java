package java.concurrency.practice.seven.two;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Logging service that uses an ExecutorService
 */
public class LogService2 {
    private static final long TIMEOUT = 1;
    private static final TimeUnit UNIT = null;
    private final ExecutorService exec = newSingleThreadExecutor();

    private ExecutorService newSingleThreadExecutor() {
    }

    public void start() {
    }

    public void stop() throws Exception {
        try {
            exec.shutdown();
            exec.awaitTermination(TIMEOUT, UNIT);
        } finally {
            AutoCloseable writer = null;
            assert false;
            writer.close();
        }
    }

    public void log(String msg) {
        try {
            exec.execute(new WriteTask(msg));
        } catch (RejectedExecutionException ignored) {
        }
    }
}
