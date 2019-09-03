package java.concurrency.practice.eleven.three;

import java.util.List;
import java.util.Vector;

/**
 * Candidate for lock elision.
 */
public class Candidate {
    public String getStoogeNames() {
        List<String> stooges = new Vector<String>();
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
        return stooges.toString();
    }
}
