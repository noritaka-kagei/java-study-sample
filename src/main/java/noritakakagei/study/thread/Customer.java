package noritakakagei.study.thread;

import java.util.Queue;

public class Customer implements Runnable {
    private final Queue<String> queue;

    public Customer(Queue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized(queue) {
                    while (queue.isEmpty()) {
                        System.out.println("Customer: waiting message ...");
                        queue.wait();
                    }

                    String message = queue.remove();
                    System.out.println("Customer: "+message+" is consumed.");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
