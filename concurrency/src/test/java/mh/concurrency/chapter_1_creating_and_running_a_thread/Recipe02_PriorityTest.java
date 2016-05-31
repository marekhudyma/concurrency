package mh.concurrency.chapter_1_creating_and_running_a_thread;

import org.junit.Test;

public class Recipe02_PriorityTest {

    private class Calculator implements Runnable {

        private int number;

        public Calculator(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            for (int i=1; i<=10; i++){
                System.out.printf("%s: %d * %d = %d\n", Thread.currentThread().getName(), number, i, i * number);
            }
        }
    }

    @Test
    public void testThread2() throws Exception {

        // Thread priority infomation
        System.out.printf("Minimum Priority: %s\n",Thread.MIN_PRIORITY);
        System.out.printf("Normal Priority: %s\n",Thread.NORM_PRIORITY);
        System.out.printf("Maximun Priority: %s\n",Thread.MAX_PRIORITY);

        Thread threads[];
        Thread.State status[];

        // Launch 10 threads to do the operation, 5 with the max
        // priority, 5 with the min
        threads=new Thread[10];
        status=new Thread.State[10];
        for (int i=0; i<10; i++){
            threads[i]=new Thread(new Calculator(i));
            if ((i%2)==0){
                threads[i].setPriority(Thread.MAX_PRIORITY); // <---------------------------------------------
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread "+i);
        }


        // Wait for the finalization of the threads. Meanwhile,
        // write the status of those threads in a file
        for (int i=0; i<10; i++){
            System.out.println(("Main : Status of Thread " + i + " : " + threads[i].getState()));
            status[i]=threads[i].getState();
        }

        for (int i=0; i<10; i++){
            threads[i].start();
        }

        boolean finish=false;
        while (!finish) {
            for (int i=0; i<10; i++){
                if (threads[i].getState()!=status[i]) {
                    writeThreadInfo(threads[i],status[i]);
                    status[i]=threads[i].getState();
                }
            }

            finish=true;
            for (int i=0; i<10; i++){
                finish=finish &&(threads[i].getState()== Thread.State.TERMINATED);
            }
        }


    }

    /**
     *  This method writes the state of a thread in a file
     * @param thread : Thread whose information will be written
     * @param state : Old state of the thread
     */
    private static void writeThreadInfo(Thread thread, Thread.State state) {
        System.out.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
        System.out.printf("Main : Priority: %d\n",thread.getPriority());
        System.out.printf("Main : Old State: %s\n",state);
        System.out.printf("Main : New State: %s\n",thread.getState());
        System.out.printf("Main : ************************************\n");
    }
}
