package java.concurrency.practice.eight.one;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Task that deadlocks in a single-threaded Executor . Don’t do this.
 */
public class ThreadDeadlock {
    ExecutorService exec = Executors.newSingleThreadExecutor();

    public class RenderPageTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Future<String> header, footer;
            header = exec.submit((Callable<String>) new LoadFileTask("header.html"));
            footer = exec.submit((Callable<String>) new LoadFileTask("footer.html"));
            String page = renderBody();
// Will deadlock -- task waiting for result of subtask
            return header.get() + page + footer.get();
        }

        private String renderBody() {
            return "longge";
        }
    }

    public RenderPageTask renderPageTask() {
        return new RenderPageTask();
    }
}
