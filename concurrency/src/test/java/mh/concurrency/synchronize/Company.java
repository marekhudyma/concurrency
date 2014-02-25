package mh.concurrency.synchronize;

public class Company implements Runnable {

    /**
     * The account affected by the operations
     */
    private Account account;

    /**
     * Constructor of the class. Initializes the account
     * @param account the account affected by the operations
     */
    public Company(Account account) {
        this.account=account;
    }

    /**
     * Core method of the Runnable
     */
    public void run() {
        for (int i=0; i<100; i++){
            account.addAmount(1000);
        }
    }

}