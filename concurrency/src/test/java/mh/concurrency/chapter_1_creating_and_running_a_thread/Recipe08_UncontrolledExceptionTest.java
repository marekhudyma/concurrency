package mh.concurrency.chapter_1_creating_and_running_a_thread;

import org.junit.Test;

public class Recipe08_UncontrolledExceptionTest {

    private static class ExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("An exception has been captured");
            System.out.println("Thread: " + t.getId());
            System.out.println("Exception: " + e.getClass().getName() + e.getMessage());
            System.out.println("Stack Trace:");
            e.printStackTrace(System.out);
            System.out.println("Thread status: " + t.getState());
        }

    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            Integer.parseInt("TTT");
        }

    }

    @Test
    public void testUncontrolledException() throws Exception {
        Task task=new Task();
        Thread thread=new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
        thread.join();
        System.out.println("Thread has finished");
    }

}
