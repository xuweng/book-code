package java.concurrency.practice.two.three;

class Widget {
    public synchronized void doSomething() {
    }
}

class LoggingWidget extends Widget {
    @Override
    public synchronized void doSomething() {
        System.out.println(toString() + ": calling doSomething");
        super.doSomething();
    }
}