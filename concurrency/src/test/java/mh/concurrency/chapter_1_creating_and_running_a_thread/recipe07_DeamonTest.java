package mh.concurrency.chapter_1_creating_and_running_a_thread;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

//when there is only deamon thread, JVM finish execution of the program
//priority is very low
public class Recipe07_DeamonTest {

    private static class Event {
        private Date date;
        private String event;

        public Event(final Date date, final String event) {
            this.date = date;
            this.event = event;
        }

        public Date getDate() {
            return date;
        }

        public String getEvent() {
            return event;
        }

    }

    //generates and event every second
    public class WriterTask implements Runnable {

        private Deque<Event> deque;

        public WriterTask(Deque<Event> deque) {
            this.deque = deque;
        }

        @Override
        public void run() {
            for (int i = 1; i < 100; i++) {
                Event event = new Event(new Date(),
                                        String.format("The thread %s has generated an event",
                                                      Thread.currentThread().getId()));
                deque.addFirst(event);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class CleanerTask extends Thread {
        private Deque<Event> deque;

        public CleanerTask(Deque<Event> deque) {
            this.deque = deque;
            setDaemon(true); // Establish that this is a Daemon Thread
        }

        @Override
        public void run() {
            while (true) {
                Date date = new Date();
                clean(date);
            }
        }

        // Method that review the Events data structure and delete the events older than ten seconds
        private void clean(Date date) {
            long difference;
            boolean delete;
            if (deque.size() == 0) {
                return;
            }
            delete = false;
            do {
                Event e = deque.getLast();
                difference = date.getTime() - e.getDate().getTime();
                if (difference > 10000) {
                    System.out.println("Cleaner: " + e.getEvent());
                    deque.removeLast();
                    delete = true;
                }
            } while (difference > 10000);
            if (delete) {
                System.out.printf("Cleaner: Size of the queue: %d\n", deque.size());
            }
        }
    }


    @Test
    public void deamonTest() throws Exception {
        Deque<Event> deque = new ArrayDeque<>();

        WriterTask writer = new WriterTask(deque);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(writer);
            thread.start();
        }

        CleanerTask cleaner = new CleanerTask(deque);
        cleaner.start();

        TimeUnit.SECONDS.sleep(30);
    }
}
