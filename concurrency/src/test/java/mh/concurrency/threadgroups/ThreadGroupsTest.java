package mh.concurrency.threadgroups;

import junit.framework.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ThreadGroupsTest {

    @Test
    public void threadGroupsTest() throws InterruptedException {

        // Create a ThreadGroup
        ThreadGroup threadGroup = new ThreadGroup("Tasks");
        Result result=new Result();

        // Create a SeachTask and 10 Thread objects with this Runnable
        SomeTask searchTask=new SomeTask(result);
        for (int i=0; i<10; i++) {
            Thread thread=new Thread(threadGroup, searchTask);
            thread.start();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Write information about the ThreadGroup to the console
        System.out.println("Number of Threads: " + threadGroup.activeCount());
        System.out.println("Information about the Thread Group - list");
        threadGroup.list();

        // Write information about the status of the Thread objects to the console
        Thread[] threads=new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (int i=0; i<threadGroup.activeCount(); i++) {
            System.out.println("Thread " + threads[i].getName() + " " + threads[i].getState());
        }

        // Wait for the finalization of some threads
        System.out.println("waiting Finish");
        while (threadGroup.activeCount()>8) {
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("interrupt");
        // Interrupt all the Thread objects assigned to the ThreadGroup
        threadGroup.interrupt();

        Thread.sleep(1000);

        Assert.assertEquals(0, threadGroup.activeCount());
    }

}
