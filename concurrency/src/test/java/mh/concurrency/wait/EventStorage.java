package mh.concurrency.wait;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EventStorage {

    /**
     * Maximum size of the storage
     */
    private int maxSize;
    /**
     * Storage of events
     */
    private List<Date> storage;

    /**
     * Constructor of the class. Initializes the attributes.
     */
    public EventStorage(){
        maxSize=10;
        storage=new LinkedList<Date>();
    }

    /**
     * This method creates and storage an event.
     */
    public synchronized void set(){
        while (storage.size()==maxSize){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.printf("Set: %d %n",storage.size());
        notify();
    }

    /**
     * This method delete the first event of the storage.
     */
    public synchronized void get(){
        while (storage.size()==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Get: %d: %s %n",storage.size(),((LinkedList<?>)storage).poll());
        notify();
    }

}