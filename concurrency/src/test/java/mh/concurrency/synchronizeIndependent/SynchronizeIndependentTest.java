package mh.concurrency.synchronizeIndependent;

import junit.framework.Assert;
import org.junit.Test;

public class SynchronizeIndependentTest {

    @Test
    public void synchronizeIndependentTest() {
        Cinema cinema=new Cinema();

        // Creates a TicketOffice1 and a Thread to run it
        TicketOffice1 ticketOffice1=new TicketOffice1(cinema);
        Thread thread1=new Thread(ticketOffice1,"TicketOffice1");

        // Creates a TicketOffice2 and a Thread to run it
        TicketOffice2 ticketOffice2=new TicketOffice2(cinema);
        Thread thread2=new Thread(ticketOffice2,"TicketOffice2");

        // Starts the threads
        thread1.start();
        thread2.start();

        try {
            // Waits for the finalization of the threads
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the vacancies in the cinemas
        System.out.printf("Room 1 Vacancies: %d\n",cinema.getVacanciesCinema1());
        System.out.printf("Room 2 Vacancies: %d\n",cinema.getVacanciesCinema2());

        Assert.assertEquals(5, cinema.getVacanciesCinema1());
        Assert.assertEquals(6, cinema.getVacanciesCinema2());
    }

}
