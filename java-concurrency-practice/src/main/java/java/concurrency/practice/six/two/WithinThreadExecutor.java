package java.concurrency.practice.six.two;

import java.util.concurrent.Executor;

/**
 * Executor that executes tasks synchronously in the calling thread.
 */
public class WithinThreadExecutor implements Executor {
    public void execute(Runnable r) {
        r.run();
    }

}
