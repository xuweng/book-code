package java.concurrency.practice.four.two;

import java.concurrency.practice.common.annotation.ThreadSafe;

/**
 * Listing 4.2. Using confinement to ensure thread safety.
 */
@ThreadSafe
public class PersonSet {
    @GuardedBy("this")
    private final Set<Person> mySet = new HashSet<Person>();

    public synchronized void addPerson(Person p) {
        mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p) {
        return mySet.contains(p);
    }
}
