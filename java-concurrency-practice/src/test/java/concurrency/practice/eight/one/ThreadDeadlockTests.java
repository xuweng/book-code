package concurrency.practice.eight.one;

import org.junit.Test;

import java.concurrency.practice.eight.one.ThreadDeadlock;

public class ThreadDeadlockTests {
    ThreadDeadlock threadDeadlock = new ThreadDeadlock();

    @Test
    public void getThreadDeadlock() throws Exception {
        ThreadDeadlock.RenderPageTask renderPageTask = threadDeadlock.renderPageTask();

        renderPageTask.call();
    }
}
