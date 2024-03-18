package noritakakagei.study.virtualthread;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class PerformanceSample {
    private static final Integer NUM_THREADS = 1_000_000;
    private static final ThreadFactory pfFactory = Thread.ofPlatform().factory();
    private static final ThreadFactory vrtFactory = Thread.ofVirtual().factory();
    
    // long run main thread
    public static void main(String... args) throws InterruptedException {
        // ExecutorService executor;

        /* Pattern: ThreadFactory object */
        // PLATFORM THREAD
        System.out.println("Running platform threads by using ThreadFactory...");
        long timeOfPfFactory = getTimeOfRunningThreadsByFactory(pfFactory);
        
        // VIRTUAL THREAD
        System.out.println("Running virtual threads by using ThreadFactory...");
        long timeOfVrtFactory = getTimeOfRunningThreadsByFactory(vrtFactory);

        // /* Pattern: ExecutorService object */
        // // PLATFORM THREAD
        // System.out.println("Running platform threads by using ExecutorService...");
        // executor = Executors.newFixedThreadPool(8, pfFactory);
        // long timeOfPfExecutor = getTimeOfRunningThreadsByExecutor(executor);

        // // VIRTUAL THREAD
        // System.out.println("Running virtual threads by using ExecutorService...");
        // executor = Executors.newFixedThreadPool(8, vrtFactory);
        // long timeOfVrtExecutor = getTimeOfRunningThreadsByExecutor(executor);

        // Output results
        System.out.println("Running Time:");
        System.out.println("Platform Thread (ThreadFactory): " + timeOfPfFactory + "(ms)");
        System.out.println("Virtual Thread (ThreadFactory): " + timeOfVrtFactory + "(ms)");
        // System.out.println("Platform Thread (ExecutorService): " + timeOfPfExecutor + "(ms)");
        // System.out.println("Virtual Thread (ExecutorService): " + timeOfVrtExecutor + "(ms)");
    }

    // return running time (milli seconds)
    protected static long getTimeOfRunningThreadsByFactory(ThreadFactory factory) 
        throws InterruptedException {

        List<Thread> threads = new ArrayList<>();

        long start = System.currentTimeMillis();

        // create and running tasks on thread
        for(int i = 0; i < NUM_THREADS; i++) {
            Thread thread = factory.newThread(new SleepWorker("Thread["+i+"]"));
            thread.start();
            threads.add(thread);
        }

        // wait tasks to finish
        for(Thread thread : threads) {
            thread.join();
        }

        long end = System.currentTimeMillis();

        return end - start;
    }

    // return running time (milli seconds)
    protected static long getTimeOfRunningThreadsByExecutor(ExecutorService executor) 
        throws InterruptedException {

        long start = System.currentTimeMillis();

        // create and running tasks on thread
        for(int i=0; i<NUM_THREADS; i++) {
            executor.submit(new SleepWorker("Thread["+i+"]"));
        }

        // wait tasks to finish
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS); // timeout 1h

        long end = System.currentTimeMillis();

        return end - start;
    }
}

class SleepWorker implements Runnable {
    private final String name;

    SleepWorker(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        // something to do
        try {
            Thread.sleep(Duration.ofSeconds(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/* Result of executing this program */
// $ java -Xms4G -Xmx4G noritakakagei.study.virtualthread.PerformanceSample
// 
// Number of threads: 1,000,000
// Executing time: create thread -> start thread -> wait thread -> done task and thread
//  Platform Thread (ThreadFactory): 323377(ms)
//  Virtual Thread (ThreadFactory): 13028(ms)
//  Platform Thread: N/A(ms) -> timeout 1h
//  Virtual Thread: N/A(ms) -> timeout 1h
// 
// Number of threads: 100,000
// Executing time:
//  Platform Thread (ThreadFactory): 35055(ms)
//  Virtual Thread (ThreadFactory): 5843(ms)
//  Platform Thread: N/A(ms) -> timeout 1h
//  Virtual Thread: N/A(ms) -> timeout 1h