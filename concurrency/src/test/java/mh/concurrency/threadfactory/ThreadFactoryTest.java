package mh.concurrency.threadfactory;

import org.junit.Test;

public class ThreadFactoryTest {

    @Test
    public void threadFactoryTest() {
        MyThreadFactory factory=new MyThreadFactory("MyThreadFactory");

        Task task=new Task();


        // Creates and starts ten Thread objects
        System.out.printf("Starting the Threads\n");
        for (int i=0; i<10; i++){
            Thread thread=factory.newThread(task);
            thread.start();
        }

        System.out.println("-----------------------");
        System.out.println(factory.getStats());
    }


}
