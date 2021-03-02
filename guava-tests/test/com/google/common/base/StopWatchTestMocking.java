package com.google.common.base;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;


import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static org.mockito.Mockito.*;

public class StopWatchTestMocking {

    private TimeUnit t1;
    private Ticker ticker;

    private Stopwatch watch;

    @Before
    public void setup() {

        t1 = mock(TimeUnit.class);
        ticker = mock(Ticker.class);
        watch = new Stopwatch(ticker);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testElapsedWhenTimeUnitIsCustomized() {
        when(t1.convert(0, NANOSECONDS)).thenReturn(5L);
        Assert.assertTrue(watch.elapsed(t1) == 5L);
        //check if this method was called for one time
        verify(t1, times(1)).convert(0, NANOSECONDS);
    }

    @Test
    public void testElapsedWthenTickerIsCustomized() {
        when(ticker.read()).thenReturn(0L);
        watch.start();
        watch.stop();
        Assert.assertTrue(watch.elapsed(MICROSECONDS) == 0L);
        verify(ticker, times(2)).read(); //read should be called twice
    }

}
