package java.concurrency.practice.eleven.four;

import java.concurrency.practice.common.annotation.GuardedBy;
import java.concurrency.practice.common.annotation.ThreadSafe;
import java.util.Set;

/**
 * ServerStatus refactored to use split locks.
 */
@ThreadSafe
public class ServerStatus2 {
    @GuardedBy("users")
    public final Set<String> users;
    @GuardedBy("queries")
    public final Set<String> queries;

    public void addUser(String u) {
        synchronized (users) {
            users.add(u);
        }
    }

    public void addQuery(String q) {
        synchronized (queries) {
            queries.add(q);
        }
    }
// remove methods similarly refactored to use split locks
}
