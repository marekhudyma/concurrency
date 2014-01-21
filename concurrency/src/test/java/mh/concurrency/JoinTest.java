package mh.concurrency;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class JoinTest {

    @Test
    public void joinTest() throws InterruptedException {

        Thread thread1 = new MyThread(1);
        Thread thread2 = new MyThread(3);
        Thread thread3 = new MyThread(60);

        thread1.start();
        thread2.start();
        thread3.start();

        System.out.println("-----1");
        thread1.join(5*1000);
        System.out.println("-----2");
        thread2.join(5*1000);
        System.out.println("-----3");
        thread3.join(5*1000);
        System.out.println("-----4");

    }

    private class MyThread extends Thread {

        private long sleepInSeconds;
        public MyThread(long sleepInSeconds) {
            this.sleepInSeconds = sleepInSeconds;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(sleepInSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
