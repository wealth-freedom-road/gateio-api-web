package io.github.road.gateio.tookit;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:fuwei13@xdf.cn">pleuvoir</a>
 */
public class CommonUtils {


    public static void sleepSeconds(Integer seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ignored) {
        }
    }

    public static void sleepMilliSeconds(Integer milliSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliSeconds);
        } catch (InterruptedException ignored) {
        }
    }

    public static void sleepMinutes(Integer minutes) {
        try {
            TimeUnit.MINUTES.sleep(minutes);
        } catch (InterruptedException ignored) {
        }
    }

    public static void sleepHours(Integer hours) {
        try {
            TimeUnit.HOURS.sleep(hours);
        } catch (InterruptedException ignored) {
        }
    }

    public static void join() {
        try {
            Thread.currentThread().join();
        } catch (InterruptedException ignored) {
        }
    }
}
