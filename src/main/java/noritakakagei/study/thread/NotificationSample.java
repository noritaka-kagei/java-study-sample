package noritakakagei.study.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Queue<String> queue = new LinkedList<>();   // this object used also as lock object
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Future<?> producer = executor.submit(new Producer(queue));
        Future<?> customer1 = executor.submit(new Customer("001", queue));
        Future<?> customer2 = executor.submit(new Customer("002", queue));

        // waiting for sub thread terminated
        producer.get();
        customer1.get();
        customer2.get();

        executor.shutdown();
    }
}