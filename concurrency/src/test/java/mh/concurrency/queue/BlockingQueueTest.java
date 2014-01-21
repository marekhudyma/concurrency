package mh.concurrency.queue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {

    @Test
    public void blockingQueueTest() throws InterruptedException {

        //get - blocks
        //put - blocks if array is not big enough
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(200* 1000);
        System.out.println("End of time");

    }
}