package mh.concurrency.synchronize;

import junit.framework.Assert;
import org.junit.Test;

public class SynchronizedTest {

    @Test
    public void fff() {

        // Creates a new account ...
        Account	account=new Account();
        // an initialize its balance to 1000
        account.setBalance(1000);

        // Creates a new Company and a Thread to run its task
        Company	company=new Company(account);
        Thread companyThread=new Thread(company);
        // Creates a new Bank and a Thread to run its task
        Bank bank=new Bank(account);
        Thread bankThread=new Thread(bank);

        // Prints the initial balance
        System.out.printf("Account : Initial Balance: %f\n",account.getBalance());

        // Starts the Threads
        companyThread.start();
        bankThread.start();

        try {
            // Wait for the finalization of the Threads
            companyThread.join();
            bankThread.join();
            // Print the final balance
            System.out.printf("Account : Final Balance: %f\n",account.getBalance());

            Assert.assertEquals(1000.0, account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
