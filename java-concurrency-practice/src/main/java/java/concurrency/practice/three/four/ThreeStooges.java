package java.concurrency.practice.three.four;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * Immutable class built out of mutable underlying objects.
 */
@Immutable
public final class ThreeStooges {
    private final Set<String> stooges = new HashSet<String>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }
}
