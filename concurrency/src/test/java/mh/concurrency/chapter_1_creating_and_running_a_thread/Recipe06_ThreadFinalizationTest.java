package mh.concurrency.chapter_1_creating_and_running_a_thread;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Recipe06_ThreadFinalizationTest {

    //Class that simulates an initialization operation. It sleeps during six seconds
    private static class NetworkConnectionsLoader implements Runnable {
        @Override
        public void run() {
            System.out.println("Begining network connections loading: " + new Date());
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Network connections loading has finished: " + new Date());
        }
    }

    private static class DataSourcesLoader implements Runnable {
        @Override
        public void run() {
            System.out.println("Begining data sources loading: " + new Date());
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Data sources loading has finished: " + new Date());
        }
    }

    @Test
    public void threadFinalization() throws Exception {
        DataSourcesLoader dsLoader = new DataSourcesLoader();
        Thread thread1 = new Thread(dsLoader,"DataSourceThread");
        thread1.start();

        NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
        Thread thread2 = new Thread(ncLoader,"NetworkConnectionLoader");
        thread2.start();



        System.out.println("---1" + new Date());
        thread1.join(); //4s
        System.out.println("---2" + new Date());
        thread2.join(); //6s
        System.out.println("---3" + new Date());

        //join waits till thread ends
        //TODO there is join(miliseconds to wait) !!!

        System.out.println("Main: Configuration has been loaded: " + new Date());
    }

}
