package mh.concurrency.threadgroups;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Class that simulates a some operation
 *
 */
public class SomeTask implements Runnable {

    /**
     * Store the name of the Thread if this Thread finish and is not interrupted
     */
    private Result result;

    /**
     * Constructor of the class
     * @param result Parameter to initialize the object that stores the results
     */
    public SomeTask(Result result) {
        this.result=result;
    }

    @Override
    public void run() {
        String name=Thread.currentThread().getName();
        System.out.println("Thread " + name + " Start");
        try {
            doTask();
            result.setName(name);
        } catch (InterruptedException e) {
            System.out.println("Thread " + name + " Interrupted");
            return;
        }
        System.out.println("Thread End " + name);
    }

    /**
     * Method that simulates the search operation
     * @throws InterruptedException Throws this exception if the Thread is interrupted
     */
    private void doTask() throws InterruptedException {
        Random random=new Random();
        int value=(int)(random.nextDouble()*30);
        System.out.println("Thread "  + Thread.currentThread().getName() + " sleeping " + value);
        TimeUnit.SECONDS.sleep(value);
    }

}