package fun.fengwk.commons.util.clock;

import fun.fengwk.commons.clock.SystemClock;
import org.junit.Test;

/**
 * 
 * @author fengwk
 */
public class SystemClockTest {

    @Test
    public void test() {
        SystemClock systemClock = new SystemClock();
        long millis = systemClock.currentTimeMillis();
        long micros = systemClock.currentTimeMicros();
        assert Math.abs(micros / 1000 - millis) <= 1;
    }

}
