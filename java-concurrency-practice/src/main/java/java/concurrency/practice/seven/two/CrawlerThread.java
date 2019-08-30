package java.concurrency.practice.seven.two;

import java.io.File;
import java.util.IdentityHashMap;

/**
 * Producer thread for IndexingService
 */
public class CrawlerThread extends Thread {
    private static final Object POISON = null;

    public void run() {
        try {
            File root;
            crawl(root);
        } catch (InterruptedException e) { / *fall through
* /} finally {
            while (true) {
                try {
                    IdentityHashMap queue;
                    queue.put(POISON);
                    break;
                } catch (InterruptedException e1) { / *retry
                        * /}
            }
        }
    }

    private void crawl(File root) throws InterruptedException {
    }
}
