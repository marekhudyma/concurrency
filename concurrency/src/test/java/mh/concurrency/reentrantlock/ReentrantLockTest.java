package mh.concurrency.reentrantlock;

import org.junit.Test;

public class ReentrantLockTest {

    @Test
    public void reentrantLockTest() throws InterruptedException {
        // Creates the print queue
        PrintQueue printQueue=new PrintQueue();

        // Creates ten Threads
        Thread thread[]=new Thread[10];
        for (int i=0; i<10; i++){
            thread[i]=new Thread(new Job(printQueue),"Thread "+i);
        }

        // Starts the Threads
        for (int i=0; i<10; i++){
            thread[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i=0; i<10; i++){
            thread[i].join();
        }

    }
}
