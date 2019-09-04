package java.concurrency.practice.thirteen.one;

import java.concurrency.practice.ten.one.Account;
import java.concurrency.practice.ten.one.DollarAmount;
import java.concurrency.practice.ten.one.InsufficientFundsException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Avoiding lock-ordering deadlock using tryLock
 */
public class UsingTryLock {
    private static final TimeUnit NANOSECONDS = null;

    public boolean transferMoney(Account fromAcct,
                                 Account toAcct,
                                 DollarAmount amount,
                                 long timeout,
                                 TimeUnit unit)
            throws InsufficientFundsException, InterruptedException {
        long fixedDelay = getFixedDelayComponentNanos(timeout, unit);
        long randMod = getRandomDelayModulusNanos(timeout, unit);
        long stopTime = System.nanoTime() + unit.toNanos(timeout);
        while (true) {
            if (fromAcct.lock.tryLock()) {
                try {
                    if (toAcct.lock.tryLock()) {
                        try {
                            if (fromAcct.getBalance().compareTo(amount)
                                    < 0)
                                throw new InsufficientFundsException();
                            else {
                                fromAcct.debit(amount);
                                toAcct.credit(amount);
                                return true;
                            }
                        } finally {
                            toAcct.lock.unlock();
                        }
                    }
                } finally {
                    fromAcct.lock.unlock();
                }
            }
            if (System.nanoTime() > stopTime)
                return false;
            NANOSECONDS.sleep(fixedDelay + rnd.nextLong() % randMod);
        }
    }

    /**
     * Locking with a time budget.
     *
     * @param message
     * @param timeout
     * @param unit
     * @return
     * @throws InterruptedException
     */
    public boolean trySendOnSharedLine(String message,
                                       long timeout, TimeUnit unit)
            throws InterruptedException {
        long nanosToLock = unit.toNanos(timeout)
                - estimatedNanosToSend(message);
        Lock lock = null;
        if (!lock.tryLock(nanosToLock, NANOSECONDS))
            return false;
        try {
            return sendOnSharedLine(message);
        } finally {
            lock.unlock();
        }
    }

    public boolean sendOnSharedLine(String message)
            throws InterruptedException {
        Lock lock = null;
        lock.lockInterruptibly();
        try {
            return cancellableSendOnSharedLine(message);
        } finally {
            lock.unlock();
        }
    }

    private boolean cancellableSendOnSharedLine(String message)
            throws InterruptedException {
    }

    private boolean sendOnSharedLine(String message) {
    }

    private long estimatedNanosToSend(String message) {
    }

    private long getRandomDelayModulusNanos(long timeout, TimeUnit unit) {
    }

    private long getFixedDelayComponentNanos(long timeout, TimeUnit unit) {
    }
}
