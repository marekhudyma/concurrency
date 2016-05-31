package mh.concurrency.chapter_1_creating_and_running_a_thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class Recipe12_ThreadFactoryTest {


    private static class MyThreadFactory implements ThreadFactory {
        private int counter;
        private String name;
        private List<String> stats;

        public MyThreadFactory(String name) {
            counter = 0;
            this.name = name;
            stats = new ArrayList<>();
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, name + "-Thread_" + counter);
            counter++;
            stats.add(String.format("Created thread %d with name %s on %s\n", t.getId(), t.getName(), new Date()));
            return t;
        }

        public String getStats() {
            StringBuffer buffer = new StringBuffer();
            Iterator<String> it = stats.iterator();
            while (it.hasNext()) {
                buffer.append(it.next());
            }
            return buffer.toString();
        }
    }


    private static class Task implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testThreadFactory() throws Exception {
        MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
        Task task = new Task();
        Thread thread;

        System.out.println("Starting the Threads");
        for (int i = 0; i < 10; i++) {
            thread = factory.newThread(task);
            thread.start();
        }

        System.out.println("Factory stats:");
        System.out.println(factory.getStats());

    }

}
