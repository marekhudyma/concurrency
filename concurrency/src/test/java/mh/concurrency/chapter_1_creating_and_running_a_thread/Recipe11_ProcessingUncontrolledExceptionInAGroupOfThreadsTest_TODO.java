package mh.concurrency.chapter_1_creating_and_running_a_thread;

import org.junit.Test;

import java.util.Random;

public class Recipe11_ProcessingUncontrolledExceptionInAGroupOfThreadsTest_TODO {

    private static class MyThreadGroup extends ThreadGroup {
        public MyThreadGroup(String name) {
            super(name);
        }
        @Override //TODO nie dziala jak trzeba ?
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("The thread " + t.getId() + " has thrown an Exception");
            e.printStackTrace(System.out);
            System.out.println("Terminating the rest of the Threads");
            interrupt();
        }
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            int result;
            Random random=new Random(Thread.currentThread().getId());
            while (true) {
                result=1000/((int)(random.nextDouble()*1000));
                System.out.println("Thread" + Thread.currentThread().getId() + " " + result);
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getId() + " Interrupted");
                    return;
                }
            }
        }
    }


    @Test
    public void testUncontrolledException() throws Exception {
        MyThreadGroup threadGroup=new MyThreadGroup("MyThreadGroup");
        Task task=new Task();
        for (int i=0; i<2; i++){
            Thread t=new Thread(threadGroup,task);
            t.start();
        }
    }

}
