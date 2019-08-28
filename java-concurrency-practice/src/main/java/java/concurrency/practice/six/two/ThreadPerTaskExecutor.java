package java.concurrency.practice.six.two;

import java.util.concurrent.Executor;

/**
 * Executor that starts a new thread for each task.
 */
public class ThreadPerTaskExecutor implements Executor {
    public void execute(Runnable r) {
        new Thread(r).start();
    }

}
