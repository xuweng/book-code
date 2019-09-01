package java.concurrency.practice.nine.four;

import java.concurrency.practice.nine.one.GuiExecutor;
import java.util.concurrent.*;

/**
 * Background task class supporting cancellation, completion notifica-
 * tion, and progress notification.
 *
 * @param <V>
 */
abstract class BackgroundTask<V> implements Runnable, Future<V> {
    private final FutureTask<V> computation = new Computation();

    private class Computation extends FutureTask<V> {
        public Computation() {
            super(new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return BackgroundTask.this.compute();
                }
            });
        }

        @Override
        protected final void done() {
            GuiExecutor.instance().execute(new Runnable() {
                @Override
                public void run() {
                    V value = null;
                    Throwable thrown = null;
                    boolean cancelled = false;
                    try {
                        value = get();
                    } catch (ExecutionException e) {
                        thrown = e.getCause();
                    } catch (CancellationException e) {
                        cancelled = true;
                    } catch (InterruptedException consumed) {
                    } finally {
                        onCompletion(value, thrown, cancelled);
                    }
                }

                ;
            });
        }
    }

    protected void setProgress(final int current, final int max) {
        GuiExecutor.instance().execute(new Runnable() {
            @Override
            public void run() {
                onProgress(current, max);
            }
        });
    }

    // Called in the background thread
    protected abstract V compute() throws Exception;

    // Called in the event thread
    protected void onCompletion(V result, Throwable exception,
                                boolean cancelled) {
    }

    protected void onProgress(int current, int max) {
    }
// Other Future methods forwarded to computation
}
