package mh.concurrency.chapter_1_creating_and_running_a_thread;

import org.junit.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Recipe04_InterruptedExceptionTest {

    private class FileSearch implements Runnable {

        private String initPath;
        private String fileName;

        public FileSearch(String initPath, String fileName) {
            this.initPath = initPath;
            this.fileName = fileName;
        }

        @Override
        public void run() {
            File file = new File(initPath);
            if (file.isDirectory()) {
                try {
                    directoryProcess(file);
                } catch (InterruptedException e) {
                    System.out.printf("%s: The search has been interrupted",Thread.currentThread().getName());
                    cleanResources();
                }
            }
        }

        private void cleanResources() {

        }

        private void directoryProcess(File file) throws InterruptedException {

            // Get the content of the directory
            File list[] = file.listFiles();
            if (list != null) {
                for (int i = 0; i < list.length; i++) {
                    if (list[i].isDirectory()) {
                        // If is a directory, process it
                        directoryProcess(list[i]);
                    } else {
                        // If is a file, process it
                        fileProcess(list[i]);
                    }
                }
            }
            // Check the interruption
            if (Thread.interrupted()) {
                throw new InterruptedException();  //--------------------------------- my decydujemy
            }
        }

        private void fileProcess(File file) throws InterruptedException {

            System.out.println(file.getAbsolutePath());

            if (file.getName().equals(fileName)) {
                System.out.printf("%s : %s",Thread.currentThread().getName() ,file.getAbsolutePath());
            }

            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
        }
    }

    @Test
    public void testInterruptedExceptionTest() throws Exception {
        FileSearch searcher=new FileSearch(System.getProperty("user.home"),"abc.txt");
        Thread thread=new Thread(searcher);
        thread.start();

        TimeUnit.SECONDS.sleep(5);

        thread.interrupt();
    }



}
