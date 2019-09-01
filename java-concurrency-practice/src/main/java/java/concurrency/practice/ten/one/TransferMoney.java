package java.concurrency.practice.ten.one;

/**
 * Dynamic lock-ordering deadlock. Don’t do this.
 */
public class TransferMoney {
    // Warning: deadlock-prone!
    public void transferMoney(Account fromAccount,
                              Account toAccount,
                              DollarAmount amount)
            throws InsufficientFundsException {
        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance().compareTo(amount) < 0)
                    throw new InsufficientFundsException();
                else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }
    }
}
