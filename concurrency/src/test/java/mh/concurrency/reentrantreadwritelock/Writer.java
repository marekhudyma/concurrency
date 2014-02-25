package mh.concurrency.reentrantreadwritelock;

public class Writer implements Runnable {

    /**
     * Class that stores the prices
     */
    private PricesInfo pricesInfo;

    /**
     * Constructor of the class
     * @param pricesInfo object that stores the prices
     */
    public Writer(PricesInfo pricesInfo){
        this.pricesInfo=pricesInfo;
    }

    /**
     * Core method of the writer. Establish the prices
     */
    @Override
    public void run() {
        for (int i=0; i<10; i++) {
            System.out.printf("Writer: Attempt to modify the prices.\n");
            double number = Math.random()*10;
            pricesInfo.setPrices(number, number);
            System.out.printf("Writer: Prices have been modified.\n");
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}