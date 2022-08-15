package ds.test.util;

public class TimeBenchmarkUtil {

    public static void timeIt(Runnable action) {
        final long start = System.currentTimeMillis();
        action.run();
        final long end = System.currentTimeMillis();
        System.out.println("Time used: " + (end - start) + " ms");
    }

    public static void timeIt(int repeatTimes, Runnable action) {
        final long start = System.currentTimeMillis();
        action.run();
        final long end = System.currentTimeMillis();
        System.out.println("Time used: " + (end - start) + " ms; repeat times : " + repeatTimes);
    }
}
