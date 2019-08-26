package java.concurrency.practice.three.two;

class UnsafeStates {
    private String[] states = new String[]{
            "AK", "AL"
    };

    public String[] getStates() {
        return states;
    }
}
