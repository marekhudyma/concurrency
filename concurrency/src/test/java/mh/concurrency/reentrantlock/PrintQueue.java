package mh.concurrency.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {

    /**
     * Lock to control the access to the queue.
     */
    private final Lock queueLock=new ReentrantLock(true);

    /**
     * Method that prints something
     */
    //WITHOUT BLOCKING - SKIPPING IF NOT ABLE TO BLOCK
    public void printJob(){
        if (queueLock.tryLock()) {
            try {
                Long duration=(long)(Math.random()*1000);
                System.out.printf("%s: PrintQueue: Printing a Job during %d miliseconds \n",Thread.currentThread().getName(),duration);
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                queueLock.unlock();
            }
        } else {
            System.out.println("Not able to lock");
        }


    }

// BLOCKS
//    public void printJob(Object document){
//        queueLock.lock();
//
//        try {
//            Long duration=(long)(Math.random()*10000);
//            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n",Thread.currentThread().getName(),(duration/1000));
//            Thread.sleep(duration);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            queueLock.unlock();
//        }
//    }
}