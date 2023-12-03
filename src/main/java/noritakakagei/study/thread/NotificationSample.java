package noritakakagei.study.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NotificationSample {
    public static void main(String[] args) throws InterruptedException, ExecutionException{
        Queue<String> queue = new LinkedList<>();   // this object used also as lock object
        ExecutorService executor = Executors.newFixedThreadPool(4);

        Future<?> producer = executor.submit(new Producer(queue));
        Future<?> customer = executor.submit(new Customer(queue));

        // waiting for sub thread terminated
        producer.get();
        customer.get();

        executor.shutdown();
    }
}
