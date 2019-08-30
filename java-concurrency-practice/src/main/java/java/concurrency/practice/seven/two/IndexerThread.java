package java.concurrency.practice.seven.two;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * Consumer thread for IndexingService .
 */
public class IndexerThread extends Thread {
    String pathname;
    private static final File POISON = new File(pathname);

    @Override
    public void run() {
        try {
            while (true) {
                BlockingQueue queue = null;
                File file = (File) queue.take();
                if (file == POISON)
                    break;
                else
                    indexFile(file);
            }
        } catch (InterruptedException consumed) {
        }
    }

    private void indexFile(File file) {
    }
}