package mh.concurrency.wait;

import org.junit.Test;

public class WaitTest {

    @Test
    public void waitNotifyTest() throws InterruptedException {
        // Creates an event storage
        EventStorage storage=new EventStorage();

        // Creates a Producer and a Thread to run it
        Producer producer=new Producer(storage);
        Thread thread1=new Thread(producer);

        // Creates a Consumer and a Thread to run it
        Consumer consumer=new Consumer(storage);
        Thread thread2=new Thread(consumer);

        // Starts the thread
        thread2.start();
        thread1.start();


        thread1.join();
        thread2.join();

    }
}
