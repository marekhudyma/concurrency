package mh.concurrency.threadlocal;

import org.junit.Test;

public class ThreadLocalTest {

    @Test
    public void threadLocalTest() {
        for(int i=0; i<1000; i++){
            Thread t1 = new Thread(new Task());
            t1.start();
        }
    }
}
