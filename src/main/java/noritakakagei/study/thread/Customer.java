package noritakakagei.study.thread;

import java.util.Queue;

public class Customer implements Runnable {
    private final String name;
    private final Queue<String> queue;

    public Customer(String name, Queue<String> queue) {
        this.name = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(10000);
                synchronized(queue) {
                    while (queue.isEmpty()) {
                        System.out.println(String.format("Customer(%s): waiting message ...", name));
                        queue.wait();
                    }

                    String message = queue.remove();
                    System.out.println(String.format("Customer(%s): %s is consumed", name, message));

                    // teminate worker thread
                    if (message.equals("quit")) break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
