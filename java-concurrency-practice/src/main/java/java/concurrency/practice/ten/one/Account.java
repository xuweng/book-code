package java.concurrency.practice.ten.one;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    public ReentrantLock lock;

    public Comparable<DollarAmount> getBalance() {
    }

    public void debit(DollarAmount amount) {
    }

    public void credit(DollarAmount amount) {
    }
}
