package gameTest;

import game.TimeCounter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TimeCounterTest {
    TimeCounter timeCounter;
    @Before
    public void init(){
        timeCounter=new TimeCounter();
        Assert.assertEquals(0, timeCounter.getValue());
        Assert.assertEquals(0, timeCounter.getElapsedTime());
    }
    @Test
    public void testStartTimeCounter() throws InterruptedException {
        timeCounter.startTimeCounter();
        Thread.sleep(6000);
        Assert.assertEquals(6, timeCounter.getValue());
        Assert.assertEquals(6, timeCounter.getElapsedTime());
    }
    @Test
    public void testCountDown() throws InterruptedException {
        testStartTimeCounter();
        //timeCounter.countDown(6);
        Thread.sleep(5000);
        Assert.assertEquals(1, timeCounter.getValue());
        Assert.assertEquals(11, timeCounter.getElapsedTime());
    }
    @Test
    public void testSetHalfTime() throws InterruptedException {
        testStartTimeCounter();
        //timeCounter.countDown(8);
        Thread.sleep(2000);
        Assert.assertEquals(6, timeCounter.getValue());
        Assert.assertEquals(8, timeCounter.getElapsedTime());
        timeCounter.setHalfTime();
        Assert.assertEquals(3, timeCounter.getValue());
        Thread.sleep(2000);
        Assert.assertEquals(1, timeCounter.getValue());
        Assert.assertEquals(10, timeCounter.getElapsedTime());
    }
}
