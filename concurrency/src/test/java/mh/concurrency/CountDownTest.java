package mh.concurrency;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CountDownTest {

    @Test
    public void ffffffffff() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(3);
        Executor executor = Executors.newFixedThreadPool(5);
        for(int i=0; i<5; i++) {
            executor.execute(new Runnable() {
                public void run() {
                    System.out.println("Waiting ...");
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    System.out.println("... finished");
                }
            });
            Thread.sleep(1000);
            latch.countDown();
        }
    }
}
