package mh.concurrency.chapter_1_creating_and_running_a_thread;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Recipe09_NotCoveredInBook_ThreadLocalTest {

    public static class SafeTask implements Runnable {

        private static ThreadLocal<Date> startDate= new ThreadLocal<Date>() {
            protected Date initialValue(){
                return new Date();
            }
        };

        @Override
        public void run() {
            System.out.println("Starting Thread: " + Thread.currentThread().getId() + startDate.get());
            try {
                TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("Thread Finished: " + Thread.currentThread().getId() + startDate.get());
        }
    }

    public static class UnsafeTask implements Runnable{
        private Date startDate;

        @Override
        public void run() {
            startDate=new Date();
            System.out.println("Starting Thread: " + Thread.currentThread().getId() + startDate);
            try {
                TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread Finished: " + Thread.currentThread().getId() + startDate);
        }

    }

    @Test
    public void testUnsafeTask() throws Exception {
        UnsafeTask task=new UnsafeTask();

        for (int i=0; i<3; i++){
            Thread thread=new Thread(task);
            thread.start();
            TimeUnit.SECONDS.sleep(2);
        }
    }

    @Test
    public void testSafeTask() throws Exception {
        SafeTask task=new SafeTask();

        for (int i=0; i<3; i++){
            Thread thread=new Thread(task);
            TimeUnit.SECONDS.sleep(2);
            thread.start();
        }

    }

}
