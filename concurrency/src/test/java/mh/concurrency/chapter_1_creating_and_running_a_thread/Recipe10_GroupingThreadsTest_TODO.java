package mh.concurrency.chapter_1_creating_and_running_a_thread;

import org.junit.Test;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

//TODO TIMED THREADS ?
public class Recipe10_GroupingThreadsTest_TODO {

    private static class Result {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class SearchTask implements Runnable {
        private Result result;
        public SearchTask(Result result) {
            this.result=result;
        }

        @Override
        public void run() {
            String name=Thread.currentThread().getName();
            System.out.printf("Thread " + name + " Start");
            try {
                doTask();
                result.setName(name);
            } catch (InterruptedException e) {
                System.out.println("Thread " + name + " Interrupted");
                return;
            }
            System.out.println("Thread" + name + " End");
        }

        /**
         * Method that simulates the search operation
         * @throws InterruptedException Throws this exception if the Thread is interrupted
         */
        private void doTask() throws InterruptedException {
            Random random=new Random((new Date()).getTime());
            int value=(int)(random.nextDouble()*100);
            System.out.println("Thread " + Thread.currentThread().getName() + " " + value);
            TimeUnit.SECONDS.sleep(value);
        }

    }

    @Test
    public void testGrouping() throws Exception {
        ThreadGroup threadGroup = new ThreadGroup("Searcher");
        Result result=new Result();

        SearchTask searchTask=new SearchTask(result);
        for (int i=0; i<5; i++) {
            Thread thread=new Thread(threadGroup, searchTask);
            thread.start();
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println("Number of Threads: " + threadGroup.activeCount());
        System.out.println("Information about the Thread Group");
        threadGroup.list();

        Thread[] threads=new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (int i=0; i<threadGroup.activeCount(); i++) {
            System.out.println("Thread " + threads[i].getName() + " " + threads[i].getState());
        }

        waitFinish(threadGroup);

        threadGroup.interrupt();
    }

    private static void waitFinish(ThreadGroup threadGroup) {
        while (threadGroup.activeCount()>9) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
