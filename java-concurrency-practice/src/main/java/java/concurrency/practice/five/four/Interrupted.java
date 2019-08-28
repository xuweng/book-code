package java.concurrency.practice.five.four;

import javafx.concurrent.Task;

import java.util.concurrent.BlockingQueue;

/**
 * Restoring the interrupted status so as not to swallow the interrupt.
 */
public class Interrupted {
    public class TaskRunnable implements Runnable {
        BlockingQueue<Task> queue;

        public void run() {
            try {
                processTask(queue.take());
            } catch (InterruptedException e) {
// restore interrupted status
                Thread.currentThread().interrupt();
            }
        }

        private void processTask(Task take) {
        }
    }
}
