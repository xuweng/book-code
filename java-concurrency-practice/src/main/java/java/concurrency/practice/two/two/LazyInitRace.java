package java.concurrency.practice.two.two;

import java.concurrency.practice.common.annotation.NotThreadSafe;

@NotThreadSafe
public class LazyInitRace {
    private ExpensiveObject instance = null;
    public ExpensiveObject getInstance() {
        if (instance == null)
            instance = new ExpensiveObject();
        return instance;
    }
}