package java.concurrency.practice.seven.three;

import java.concurrency.practice.seven.two.LogService;

/**
 * Registering a shutdown hook to stop the logging service.
 */
public class ShutdownHooks {
    public void start() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    LogService.this.stop();
                } catch (InterruptedException ignored) {
                }
            }
        });
    }
}
