package java.concurrency.practice.eight.five;

import javax.lang.model.element.Element;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Transforming sequential execution into parallel execution.
 */
public class Parallel {
    void processSequentially(List<Element> elements) {
        for (Element e : elements)
            process(e);
    }

    private void process(Element e) {
    }

    void processInParallel(Executor exec, List<Element> elements) {
        for (final Element e : elements)
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    process(e);
                }
            });
    }

    /**
     * Transforming sequential tail-recursion into parallelized recursion.
     *
     * @param nodes
     * @param results
     * @param <T>
     */
    public <T> void sequentialRecursive(List<Node<T>> nodes,
                                        Collection<T> results) {
        for (Node<T> n : nodes) {
            results.add(n.compute());
            sequentialRecursive((List<Node<T>>) n.getChildren(), results);
        }
    }

    public <T> void parallelRecursive(final Executor exec,
                                      List<Node<T>> nodes,
                                      final Collection<T> results) {
        for (final Node<T> n : nodes) {
            exec.execute(new Runnable() {
                public void run() {
                    results.add(n.compute());
                }
            });
            parallelRecursive(exec, (List<Node<T>>) n.getChildren(), results);
        }
    }

    /**
     * Waiting for results to be calculated in parallel.
     *
     * @param nodes
     * @param <T>
     * @return
     * @throws InterruptedException
     */
    public <T> Collection<T> getParallelResults(List<Node<T>> nodes)
            throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Queue<T> resultQueue = new ConcurrentLinkedQueue<T>();
        parallelRecursive(exec, nodes, resultQueue);
        exec.shutdown();
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        return resultQueue;
    }
}
