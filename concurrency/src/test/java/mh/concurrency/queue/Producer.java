package mh.concurrency.queue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

    protected BlockingQueue<String> queue = null;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            for(int i=0; i<100; i++) {
                System.out.println("Producer: put " + String.valueOf(i));
                queue.put(String.valueOf(i));
                Thread.sleep(i*100);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}