package java.concurrency.practice.seven.one;

import java.util.concurrent.*;

import static java.concurrency.practice.five.five.Preloader.launderThrowable;

/**
 * Scheduling an interrupt on a borrowed thread. Don’t do this.
 */
public class Scheduling {
    private static final ScheduledExecutorService cancelExec = null;

    public static void timedRun(Runnable r,
                                long timeout, TimeUnit unit) {
        final Thread taskThread = Thread.currentThread();
        cancelExec.schedule(new Runnable() {
            public void run() {
                taskThread.interrupt();
            }
        }, timeout, unit);
        r.run();
    }

    /**
     * Interrupting a task in a dedicated thread.
     *
     * @param r
     * @param timeout
     * @param unit
     * @throws Exception
     */
    public static void timedRun2(final Runnable r,
                                 long timeout, TimeUnit unit)
            throws Exception {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            void rethrow() throws Exception {
                if (t != null)
                    throw launderThrowable(t);
            }

            private Exception launderThrowable(Throwable t) {
            }
        }
        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(new Runnable() {
            public void run() {
                taskThread.interrupt();
            }
        }, timeout, unit);
        taskThread.join(unit.toMillis(timeout));
        task.rethrow();
    }

    /**
     * Cancelling a task using Future
     *
     * @param r
     * @param timeout
     * @param unit
     * @throws InterruptedException
     */
    public static void timedRun(Runnable r,
                                long timeout, TimeUnit unit)
            throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {
// task will be cancelled below
        } catch (ExecutionException e) {
// exception thrown in task; rethrow
            throw launderThrowable(e.getCause());
        } finally {
// Harmless if task already completed
            task.cancel(true); // interrupt if running
        }
    }
}
