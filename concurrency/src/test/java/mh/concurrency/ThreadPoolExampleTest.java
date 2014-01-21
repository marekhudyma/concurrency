package mh.concurrency;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

public class ThreadPoolExampleTest {

//TODO read
//    http://akka.io/
//    http://en.wikipedia.org/wiki/Software_transactional_memory#Java
//    countdownlatch
//    http://pragprog.com/book/vspcon/programming-concurrency-on-the-jvm
    //SynchronousQueue
    //read file concurrently !!!

    //http://visualvm.java.net/
//ForkJoinPool

    private int getOptimalThreadNumber() {
        return Runtime.getRuntime().availableProcessors() * 4;
    }

    private void executeThreads(ExecutorService pool) {
        List<Future<Long>> futures = new ArrayList<Future<Long>>();

        for (int i = 0; i < 100; i++) { //add new elements
            futures.add(pool.submit(new Callable<Long>() {
                public Long call() throws Exception {
                    long randomLong = Long.valueOf(new Random().nextInt(100));
                    if(randomLong % 10 == 0) {
                        throw new RuntimeException();
                    }
                    Thread.sleep(randomLong);
                    return Calendar.getInstance().getTimeInMillis();
                }
            }));
        }
        futures.get(99).cancel(true);

        //iterate through elements (blocks if something is not completed)
        for (Future<Long> future : futures) {
            if (!future.isCancelled()) {
                try {
                    System.out.println(future.get());
                } catch (Exception e) {
                    System.out.println("exception");
                }
            }

            else {
                System.out.println("cancelled");
            }
        }

        pool.shutdown(); //shutdown the pool
    }

    //Keeps a permanent total number of threads
    //Threads are not started until we start submitting jobs - why?
    @Test
    public void newFixedThreadPoolTest() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(getOptimalThreadNumber());
        executeThreads(pool);

    }

    //Like a FixedThreadPool, but with only one thread
    @Test
    public void newSingleThreadExecutorTest() throws ExecutionException, InterruptedException {

        ExecutorService pool = Executors.newSingleThreadExecutor();
        executeThreads(pool);
    }

    //Creates new threads as needed and destroys old threads
    @Test
    public void newCachedThreadPoolTest() throws ExecutionException, InterruptedException {

        ExecutorService pool = Executors.newCachedThreadPool();
        executeThreads(pool);
    }


    //Should be used instead of Timer
    //run periodically
    @Test
    public void runTimerPeriodically() throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ScheduledFuture scheduledFuture =
            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                public void run() {

                    System.out.println("Executed!");
                }
            },
            200,
            100,
            TimeUnit.MILLISECONDS);
        Thread.sleep(1000);
        scheduledExecutorService.shutdown();
    }

    @Test
    public void runWithDelay() throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        ScheduledFuture scheduledFuture =
            scheduledExecutorService.schedule(new Callable() {
                public Object call() throws Exception {
                    System.out.println("Executed!");
                    return "Called!";
                }
            },
            500,
            TimeUnit.MILLISECONDS);
        Thread.sleep(1000);
        scheduledExecutorService.shutdown();
    }

}
