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
        ExecutorService executor;
        List<Thread> threads = new ArrayList<>();
        long start;
        long end;

        // PLATFORM THREAD - ThreadFactory PATTERN
        System.out.println("Running platform threads by ThreadFactory...");
        start = System.currentTimeMillis();

        for(int i = 0; i < NUM_THREADS; i++) {
            Thread thread = pfFactory.newThread(new SleepWorker("Platform Thread["+i+"]"));
            threads.add(thread);
            thread.start();
        }

        for(Thread thread : threads) {
            thread.join();
        }

        end = System.currentTimeMillis();
        long pfFactoryTime = end - start;
        
        // VIRTUAL THREAD - ThreadFactory PATTERN
        System.out.println("Running virtual threads by ThreadFactory...");
        start = System.currentTimeMillis();

        for(int i = 0; i < NUM_THREADS; i++) {
            Thread thread = vrtFactory.newThread(new SleepWorker("Virtual Thread["+i+"]"));
            threads.add(thread);
            thread.start();
        }

        for(Thread thread : threads) {
            thread.join();
        }

        end = System.currentTimeMillis();
        long vrtFactoryTime = end - start;

        // PLATFORM THREAD
        System.out.println("Running platform threads by ExecutorService...");
        executor = Executors.newFixedThreadPool(8, pfFactory);
        start = System.currentTimeMillis();

        for(int i = 0; i < NUM_THREADS; i++) {
            executor.submit(new SleepWorker("Platform Thread["+i+"]"));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);

        end = System.currentTimeMillis();
        long pfTime = end - start;

        // VIRTUAL THREAD - ExecutorService PATTERN
        System.out.println("Running virtual threads by ExecutorService...");
        start = System.currentTimeMillis();

        executor = Executors.newFixedThreadPool(8, vrtFactory);

        for(int i = 0; i < NUM_THREADS; i++) {
            executor.submit(new SleepWorker("Virtual Thread["+i+"]"));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);

        end = System.currentTimeMillis();
        long vrtTime = end - start;

        // RESULT OUTPUT
        System.out.println("Result:");
        System.out.println("Platform Thread (ThreadFactory): " + pfFactoryTime + "(ms)");
        System.out.println("Virtual Thread (ThreadFactory): " + vrtFactoryTime + "(ms)");
        System.out.println("Platform Thread (ExecutorService): " + pfTime + "(ms)");
        System.out.println("Virtual Thread (ExecutorService): " + vrtTime + "(ms)");
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
            // System.out.println(name+": sleepting");
            Thread.sleep(Duration.ofSeconds(5));
            // System.out.println(name+": wake up");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

/* Result of executing this program */
// $ java -Xms4G -Xmx4G noritakakagei.study.virtualthread.PerformanceSample
// 
// Number of threads: 1,000,000
// Executing time:
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