/*
 * study case: synchronized method
 * can use monitor lock of Counter object when enable synchronized method
 */
package noritakakagei.study.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Counter {
    private int count = 0;

    // public void increment() { count++; }
    public synchronized void increment() { count++; }
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

public class SynchronizedSample {
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
