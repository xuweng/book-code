package java.concurrency.practice.seven.one;

import javafx.concurrent.Task;

import java.util.concurrent.BlockingQueue;

public class Interrupted {
    BlockingQueue<Task> queue;

    /**
     * Propagating InterruptedException to callers.
     *
     * @return
     * @throws InterruptedException
     */
    public Task getNextTask() throws InterruptedException {
        return queue.take();
    }

    /**
     * Noncancelable task that restores interruption before exit.
     *
     * @param queue
     * @return
     */
    public Task getNextTask(BlockingQueue<Task> queue) {
        boolean interrupted = false;
        try {
            while (true) {
                try {
                    return queue.take();
                } catch (InterruptedException e) {
                    interrupted = true;
// fall through and retry
                }
            }
        } finally {
            if (interrupted)
                Thread.currentThread().interrupt();
        }
    }
}
