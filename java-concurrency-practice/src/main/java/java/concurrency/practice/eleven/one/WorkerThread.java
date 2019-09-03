package java.concurrency.practice.eleven.one;

import java.util.concurrent.BlockingQueue;

/**
 * Serialized access to a task queue.
 */
public class WorkerThread extends Thread {
    private final BlockingQueue<Runnable> queue;

    public WorkerThread(BlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Runnable task = queue.take();
                task.run();
            } catch (InterruptedException e) {
                break;
//                / *Allow thread to exit
//* /
            }
        }
    }
}
