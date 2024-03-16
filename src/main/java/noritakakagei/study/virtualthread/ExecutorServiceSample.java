package noritakakagei.study.virtualthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static java.util.stream.IntStream.range;

// only works on Java 21+
public class ExecutorServiceSample {
    public static void main(String... args) throws InterruptedException {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        Future<?> ret1 = executor.submit(new Worker("仮想スレッド1"));
        Future<?> ret2 = executor.submit(new Worker("仮想スレッド2"));

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}

class Worker implements Runnable {
    private final String name;

    Worker(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        range(0, 1000).forEach(n -> {
            System.out.printf("%s, %d\n", name, n);
        });
    }
}