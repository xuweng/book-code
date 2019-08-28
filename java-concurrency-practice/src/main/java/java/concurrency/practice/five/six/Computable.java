package java.concurrency.practice.five.six;

public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}