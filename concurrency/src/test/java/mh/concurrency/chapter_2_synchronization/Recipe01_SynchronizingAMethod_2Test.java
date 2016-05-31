package mh.concurrency.chapter_2_synchronization;

import org.junit.Test;

import java.util.Date;

public class Recipe01_SynchronizingAMethod_2Test {

    private static class ValueHolder {

        private int value = 0;

        //synchronized
        public int getValue() {
            return value;
        }

        public synchronized void add(int adder) {
            this.value = value + adder;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Changer implements Runnable {
        private ValueHolder valueHolder;

        public Changer(ValueHolder valueHolder) {
            this.valueHolder = valueHolder;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                valueHolder.add(1000);
            }
        }
    }

    @Test
    public void testCallingNotSynchronizedMethod() throws Exception {
        ValueHolder valueHolder = new ValueHolder();

        Changer changer = new Changer(valueHolder);
        Thread thread = new Thread(changer);

        System.out.println("Value : " + valueHolder.getValue() + " " + new Date());

        thread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("Value : " + valueHolder.getValue() + " " + new Date());
            Thread.sleep(100);
        }

        thread.join();

        System.out.println("Value : " + valueHolder.getValue() + " " + new Date());
    }

}
