package mh.concurrency.threadlocal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PerThreadFormatter {

    private static final ThreadLocal<SimpleDateFormat> dateFormatHolder = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            System.out.println("Creating SimpleDateFormat for Thread : " + Thread.currentThread().getName());
            return new SimpleDateFormat("dd/MM/yyyy");
        }
    };

    public static DateFormat getDateFormatter() {
        return dateFormatHolder.get();
    }

}
