package mh.concurrency.queue;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {

//    ArrayBlockingQueue
//    Blocking queue based on an array
//    Fixed length – size of array
//    Contention makes this queue quite slow – In contention situation, it locks on both puts and gets, since the internal array is modified

//    LinkedBlockingQueue
//    Blocking queue based on a linked list
//    Maximum length is unbounded by default – You can specify a maximum length

//    PriorityBlockingQueue
//    Orders elements using java.lang.Comparable or java.util.Comparator
//    Smallest element returned by take()
//    Iterator does not return ordered list – You should not iterate over queues anyway
//    For non-blocking version, see java.util.PriorityQueue

//    DelayQueue
//    Similar to PriorityBlockingQueue
//    Takes java.util.concurrent.Delayed objects
//    Only offers elements with take() once delay is elapsed

//    SynchronousQueue
//    Has a size of zero!
//    Blocks put() until take() is called by other thread
//    Don’t confuse synchronous with synchronized – Synchronous as opposed to asynchronous

    @Test
    public void blockingQueueTest() throws InterruptedException {

        //get - blocks
        //put - blocks if array is not big enough

        //New function put() for adding elements to queue – Will block if queue is bounded and full
        //New function take() waits until object arrives
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(20 * 1000);
        System.out.println("End of time");

    }
}
