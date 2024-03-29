package java.concurrency.practice.eleven.four;

import java.concurrency.practice.common.annotation.GuardedBy;
import java.concurrency.practice.common.annotation.ThreadSafe;
import java.util.Set;

/**
 * Candidate for lock splitting.
 */
@ThreadSafe
public class ServerStatus {
    @GuardedBy("this")
    public final Set<String> users;
    @GuardedBy("this")
    public final Set<String> queries;

    public synchronized void addUser(String u) {
        users.add(u);
    }

    public synchronized void addQuery(String q) {
        queries.add(q);
    }

    public synchronized void removeUser(String u) {
        users.remove(u);
    }

    public synchronized void removeQuery(String q) {
        queries.remove(q);
    }
}
