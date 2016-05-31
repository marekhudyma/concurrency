package mh.concurrency.chapter_1_creating_and_running_a_thread;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class Recipe03_InterruptTest_TODO {

    private class PrimeGenerator extends Thread {

        @Override
        public void run() {
            long number = 1L;
            while (true) {
                if (isPrime(number)) {
                    System.out.println(String.format("Number %d is Prime", number));
                }
                System.out.println(getState());

                // interrupted() - returns the boolan if thread is interrupted and set is to "false".
                // it is not recommended to use it.
                if (isInterrupted()) {
                    System.out.println(getState());  // <--------------------------------------------------- RUNNABLE ?
                    System.out.println("The Prime Generator has been Interrupted");
                    return;
                }
                number++;
            }
        }

        private boolean isPrime(long number) {
            if (number <= 2) {
                return true;
            }
            for (long i = 2; i < number; i++) {
                if ((number % i) == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    @Test
    public void testInterrupt() throws Exception {
        Thread task = new PrimeGenerator();
        task.start();

        TimeUnit.SECONDS.sleep(5);

        task.interrupt();
    }

}
