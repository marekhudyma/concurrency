package mh.concurrency.threadgroups2;

import java.util.Random;

/**
 * Class that implements the concurrent task
 *
 */
public class Task implements Runnable {

    @Override
    public void run() {
        while (true) {

            throw new RuntimeException();
        }
    }
}
