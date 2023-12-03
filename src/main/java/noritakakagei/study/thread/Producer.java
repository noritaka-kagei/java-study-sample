package noritakakagei.study.thread;

import java.io.Console;
import java.util.Queue;

public class Producer implements Runnable {
    private final Queue<String> queue;

    public Producer(Queue<String> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        while (true) {
            System.out.println("Producer: accepting message ... (Please input the message)");
            Console console = System.console();
            String message = console.readLine();
            
            synchronized(queue) {
                queue.add(message);
                queue.notifyAll();
            }
        }
    }
}
