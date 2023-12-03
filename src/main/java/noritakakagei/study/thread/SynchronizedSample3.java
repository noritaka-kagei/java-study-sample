/*
 * study case: synchronized statement (lock object)
 * can use monitor lock of Object instance (lock object) when enable synchronized statement
 */
package noritakakagei.study.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Counter {
    private int count = 0;
    private final Object lock = new Object();

    public void increment() { 
        synchronized(lock) {
            count++;
        }
    }
    public void show() { System.out.println(count); }
}

class Worker implements Runnable {
    private static final int NUM_LOOP = 10000000;
    private final Counter counter;

    Worker(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i=0; i<NUM_LOOP; i++) {
            counter.increment();
        }
    }
}

public class SynchronizedSample3 {
    // main thread
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        Counter counter = new Counter();

        Future<?> result1 = executor.submit(new Worker(counter));
        Future<?> result2 = executor.submit(new Worker(counter));

        result1.get();
        result2.get();

        executor.shutdown();

        counter.show();
    }
}
