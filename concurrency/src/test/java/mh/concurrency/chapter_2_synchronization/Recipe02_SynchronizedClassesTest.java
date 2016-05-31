package mh.concurrency.chapter_2_synchronization;


import org.junit.Test;

public class Recipe02_SynchronizedClassesTest {

    private static class Cinema {

        private long vacanciesCinema1;
        private long vacanciesCinema2;

        private final Object controlCinema1, controlCinema2;

        public Cinema(){
            controlCinema1=new Object();
            controlCinema2=new Object();
            vacanciesCinema1=20;
            vacanciesCinema2=20;
        }

        public boolean sellTickets1 (int number) {
            synchronized (controlCinema1) {
                if (number<vacanciesCinema1) {
                    vacanciesCinema1-=number;
                    return true;
                } else {
                    return false;
                }
            }
        }

        public boolean sellTickets2 (int number){
            synchronized (controlCinema2) {
                if (number<vacanciesCinema2) {
                    vacanciesCinema2-=number;
                    return true;
                } else {
                    return false;
                }
            }
        }

        public boolean returnTickets1 (int number) {
            synchronized (controlCinema1) {
                vacanciesCinema1+=number;
                return true;
            }
        }

        public boolean returnTickets2 (int number) {
            synchronized (controlCinema2) {
                vacanciesCinema2+=number;
                return true;
            }
        }

        public long getVacanciesCinema1() {
            return vacanciesCinema1;
        }

        public long getVacanciesCinema2() {
            return vacanciesCinema2;
        }

    }

    private static class TicketOffice1 implements Runnable {

        private Cinema cinema;

        public TicketOffice1 (Cinema cinema) {
            this.cinema=cinema;
        }

        @Override
        public void run() {
            cinema.sellTickets1(3);
            cinema.sellTickets1(2);
            cinema.sellTickets2(2);
            cinema.returnTickets1(3);
            cinema.sellTickets1(5);
            cinema.sellTickets2(2);
            cinema.sellTickets2(2);
            cinema.sellTickets2(2);
        }

    }

    private static class TicketOffice2 implements Runnable {

        private Cinema cinema;

        public TicketOffice2(Cinema cinema){
            this.cinema=cinema;
        }

        @Override
        public void run() {
            cinema.sellTickets2(2);
            cinema.sellTickets2(4);
            cinema.sellTickets1(2);
            cinema.sellTickets1(1);
            cinema.returnTickets2(2);
            cinema.sellTickets1(3);
            cinema.sellTickets2(2);
            cinema.sellTickets1(2);
        }

    }

    @Test
    public void testSynchronizedClassTest() throws Exception {
        Cinema cinema = new Cinema();

        TicketOffice1 ticketOffice1 = new TicketOffice1(cinema);
        Thread thread1 = new Thread(ticketOffice1, "TicketOffice1");

        TicketOffice2 ticketOffice2 = new TicketOffice2(cinema);
        Thread thread2 = new Thread(ticketOffice2, "TicketOffice2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Room 1 Vacancies: " + cinema.getVacanciesCinema1());
        System.out.println("Room 2 Vacancies: " + cinema.getVacanciesCinema2());
    }

}
