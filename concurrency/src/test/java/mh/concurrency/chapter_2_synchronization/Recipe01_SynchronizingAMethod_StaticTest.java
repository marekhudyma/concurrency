package mh.concurrency.chapter_2_synchronization;

import org.junit.Test;

public class Recipe01_SynchronizingAMethod_StaticTest {

    private static class Utility {

        public void printNonStaticNonSynchronized() {
            System.out.println("printNonStaticNonSynchronized start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("printNonStaticNonSynchronized end");
        }

        public synchronized void printNonStaticSynchronized() {
            System.out.println("printNonStaticSynchronized start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("printNonStaticSynchronized end");
        }

        public void printNonStaticSynchronizedThis() {
            System.out.println("printNonStaticSynchronizedThis start");
            synchronized (this) {
                System.out.println("printNonStaticSynchronizedThis insideSynchronizedBlock");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("printNonStaticSynchronizedThis end");
        }

        public static void printStaticNonSynchronized() {
            System.out.println("printStaticNonSynchronized start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("printStaticNonSynchronized end");
        }

        public static synchronized void printStaticSynchronized() {
            System.out.println("sprintStaticSynchronized start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("printStaticSynchronized end");
        }

        public static synchronized void printStaticSynchronized2() {
            System.out.println("sprintStaticSynchronized2 start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("printStaticSynchronized2 end");
        }
    }

    //you can call only one static synchronized method
    @Test
    public void testCallingTwoStaticSynchronizedMethods() throws Exception {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Utility.printStaticSynchronized();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Utility.printStaticSynchronized2();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    //you can call two methods (one static synchronized, one just static) at once
    @Test
    public void testCallingOneStaticSynchronizedAndStaticNonSynchronized() throws Exception {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Utility.printStaticSynchronized();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Utility.printStaticNonSynchronized();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    //you can call two methods (one static synchronized, one just non-static, not synchronized) at once
    @Test
    public void testCallingOneStaticSynchronizedAndNonStaticNotSynchronized() throws Exception {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Utility.printStaticSynchronized();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Utility utility = new Utility();
                utility.printNonStaticNonSynchronized();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    //you can call two methods (one static synchronized, one static synchronized) at once
    @Test
    public void testCallingOneStaticSynchronizedAndNonStaticSynchronized() throws Exception {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Utility.printStaticSynchronized();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Utility utility = new Utility();
                utility.printNonStaticSynchronized();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    //synchronized(this) {...} work the same like synchronized method
    @Test
    public void testCallingOneNonStaticSynchronizedAndNonStaticSynchronizedThis() throws Exception {
        final Utility utility = new Utility();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                utility.printNonStaticSynchronized();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                utility.printNonStaticSynchronizedThis();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }


}
