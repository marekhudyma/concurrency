package mh.concurrency;

import org.junit.Test;

public class UncaughtExceptionHandlerTest {

    @Test
    public void UncaughtExceptionHandlerTest() {
        Task task=new Task();
        Thread thread=new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }

    class Task implements Runnable {
        @Override
        public void run() {
            while(true){
                Integer.parseInt("TTT");
            }
        }
    }

    class ExceptionHandler implements Thread.UncaughtExceptionHandler {
        public void uncaughtException(Thread t, Throwable e) {
            System.out.printf("An exception has been captured\n");
            System.out.printf("Thread: %s\n",t.getId());
            System.out.printf("Exception: %s: %s\n",e.getClass().getName(),e.getMessage());
            System.out.printf("Stack Trace: \n");
            e.printStackTrace(System.out);
            System.out.printf("Thread status: %s\n",t.getState());
        }
    }

}
