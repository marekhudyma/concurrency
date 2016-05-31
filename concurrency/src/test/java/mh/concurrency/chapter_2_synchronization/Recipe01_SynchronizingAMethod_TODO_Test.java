package mh.concurrency.chapter_2_synchronization;

import org.junit.Test;

import java.util.Date;

public class Recipe01_SynchronizingAMethod_TODO_Test {

    private static class Account {

        private double balance;

        public Account(final double balance) {
            this.balance = balance;
        }

        public double getBalance() {
            return balance;
        }

        public synchronized void addAmount(double amount) {
            double tmp = balance;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tmp += amount;
            balance = tmp;
        }

        public synchronized void subtractAmount(double amount) {
            double tmp = balance;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tmp -= amount;
            balance = tmp;
        }

    }

    private static class Bank implements Runnable {
        private Account account;

        public Bank(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                account.subtractAmount(1000);
            }
        }
    }

    public class Company implements Runnable {
        private Account account;

        public Company(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                account.addAmount(1000);
            }
        }
    }

    // If another thread tried to access any method declared with the synchronized keyword of the same object,
    // it will be suspended untill the first thread finished the execution of the method
    //
    // TODO only one synchronized method can be executed on object concurrently
    // (in the meantime you can call non-synchronized methods)
    // synchronized method() = { synchronized(this) {... }
    //
    // static methods have different behavior. Only one execution thread will access one of the static methods
    // declared with the synchronized keyword, but another thread can access other non-static methods
    // of an object of that class.
    //
    // you can call _only_ one static synchronized method !
    // you can call two methods (one static synchronized, one just static) at once
    // you can call two methods (one static synchronized, one just non-static, not synchronized) at once
    // you can call two methods (one static synchronized, one static synchronized) at once
    @Test
    public void testSynchronizing() throws Exception {
        Account account = new Account(1000);

        Company company = new Company(account);
        Thread companyThread = new Thread(company);

        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank);


        System.out.println("Account : Initial Balance: " + account.getBalance() + " " + new Date());

        companyThread.start();
        bankThread.start();

        companyThread.join();
        bankThread.join();

        System.out.println("Account : Final Balance: " + account.getBalance() + " " + new Date());

        // execution 20 s = 2 threads x 100 executions x 0.1s = 20 s
    }

}
