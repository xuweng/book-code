package java.concurrency.practice.fifteen.four;

import org.junit.runner.notification.RunListener;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Nonblocking stack using Treiber’s algorithm (Treiber, 1986).
 * 失败重试
 * 一个循环里面
 * 非阻塞算法,循环里面的代码怎么写?
 *
 * @param <E>
 */
@RunListener.ThreadSafe
public class ConcurrentStack<E> {
    AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();

    public void push(E item) {
        Node<E> newHead = new Node<E>(item);
        Node<E> oldHead;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        } while (!top.compareAndSet(oldHead, newHead));
    }

    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = top.get();
            if (oldHead == null)
                return null;
            newHead = oldHead.next;
        } while (!top.compareAndSet(oldHead, newHead));
        return oldHead.item;
    }

    private static class Node<E> {
        public final E item;
        public Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }
}
