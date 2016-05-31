package mh.concurrency.chapter_1_creating_and_running_a_thread;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Recipe05_SleepingAndResumingTest_TODO {

    private static class FileClock implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(new Date());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.printf("The FileClock has been interrupted");
                }
            }
        }
    }

    @Test
    public void test() throws Exception {
        FileClock clock=new FileClock();
        Thread thread=new Thread(clock);

        thread.start();

        TimeUnit.SECONDS.sleep(5);

        thread.interrupt();

//        Sun May 29 22:17:49 CEST 2016
//        Sun May 29 22:17:50 CEST 2016
//        Sun May 29 22:17:51 CEST 2016
//        Sun May 29 22:17:52 CEST 2016
//        Sun May 29 22:17:53 CEST 2016
//        The FileClock has been interruptedSun May 29 22:17:54 CEST 2016 <-------- WHY ???????????
    }

}
