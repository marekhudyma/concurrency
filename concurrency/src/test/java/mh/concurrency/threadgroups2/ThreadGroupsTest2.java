package mh.concurrency.threadgroups2;


import junit.framework.Assert;
import org.junit.Test;

public class ThreadGroupsTest2 {

    @Test
    public void threadGroupsTest() throws InterruptedException {

        // Create a MyThreadGroup object
        MyThreadGroup threadGroup=new MyThreadGroup("MyThreadGroup");
        // Create and start two Thread objects for this Task
        Thread t= null;
        for (int i=0; i<20; i++){
            t=new Thread(threadGroup,new Task());
            t.start();
        }
        t.interrupt();

        Thread.sleep(1000);

        Assert.assertEquals(0, threadGroup.activeCount());


    }
}
