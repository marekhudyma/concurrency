package mh.concurrency.reentrantlock;

public class Job implements Runnable {

    /**
     * Queue to print the documents
     */
    private PrintQueue printQueue;

    /**
     * Constructor of the class. Initializes the queue
     * @param printQueue
     */
    public Job(PrintQueue printQueue){
        this.printQueue=printQueue;
    }

    /**
     * Core method of the Job. Sends the document to the print queue and waits
     *  for its finalization
     */
    @Override
    public void run() {
        System.out.printf("%s: BEFORE \n",Thread.currentThread().getName());
        printQueue.printJob();
        System.out.printf("%s: AFTER  \n",Thread.currentThread().getName());
    }
}