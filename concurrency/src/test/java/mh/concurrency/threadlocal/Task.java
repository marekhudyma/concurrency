package mh.concurrency.threadlocal;

import java.util.Date;

class Task implements Runnable{

    @Override
    public void run() {
        for(int i=0; i<2; i++){
            System.out.println("Thread: " + Thread.currentThread().getName() +
                    " Formatted Date: " + PerThreadFormatter.getDateFormatter().format(new Date()) );
        }
    }
}

