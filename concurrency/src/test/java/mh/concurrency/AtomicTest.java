package mh.concurrency;

import junit.framework.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicTest {

    @Test
    public void atomicReferenceTest() {
        Object object1 = new Object();
        Object object2 = new Object();
        Object object3 = new Object();

        AtomicReference var = new AtomicReference(object1);
        var.compareAndSet(object1, object2);
        Assert.assertEquals(object2, var.get());
        Assert.assertEquals(object2, var.getAndSet(object3));
        Assert.assertEquals(object3, var.get());
        var.weakCompareAndSet(object3, object1);
        Assert.assertEquals(object1, var.get());
        var.weakCompareAndSet(object3, object1);
        Assert.assertEquals(object1, var.get());
    }

    @Test
    public void atomicBooleanTest() {

        AtomicBoolean var = new AtomicBoolean(false);
        var.compareAndSet(true, false);
        Assert.assertEquals(false, var.get());
        Assert.assertEquals(false, var.getAndSet(true));
        Assert.assertEquals(true, var.get());
    }

    @Test
    public void atomicLongTest() {
        AtomicLong var = new AtomicLong(1);
        Assert.assertEquals(1, var.get());
        Assert.assertEquals(2, var.incrementAndGet());
        Assert.assertEquals(4, var.addAndGet(2));
        Assert.assertEquals(4, var.getAndSet(5));
        Assert.assertEquals(5, var.get());
        var.weakCompareAndSet(5,6);
        Assert.assertEquals(6, var.get());
        var.weakCompareAndSet(10,11);
        Assert.assertEquals(6, var.get());
    }

    @Test
    public void atomicIntegerTest() {
        AtomicInteger var = new AtomicInteger(1);
        Assert.assertEquals(1, var.get());
        Assert.assertEquals(2, var.incrementAndGet());
        Assert.assertEquals(4, var.addAndGet(2));
        Assert.assertEquals(4, var.getAndSet(5));
        Assert.assertEquals(5, var.get());
        var.weakCompareAndSet(5,6);
        Assert.assertEquals(6, var.get());
        var.weakCompareAndSet(10,11);
        Assert.assertEquals(6, var.get());
    }


}
