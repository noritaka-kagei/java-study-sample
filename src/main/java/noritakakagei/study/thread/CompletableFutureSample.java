package noritakakagei.study.thread;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;


// reference: [Book] Perfect Java 2 (p.450)
public class CompletableFutureSample {
    private static final Logger logger = Logger.getLogger(CompletableFutureSample.class.getName());

    // this class will be executed as a task
    private static class Worker {
        public String exec() {
            emulateLongTask();
            return "hello";
        }

        public Integer aggregate(String input) {
            logger.log(Level.INFO, "message = {0}", input);
            return input.length();
        }

        public void show(Integer result, Throwable error) {
            if (error != null) {
                logger.warning("found error");
                return;
            }
            logger.log(Level.INFO, "result = {0}", result);
        }

        private void emulateLongTask() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.log(Level.WARNING, "Interrupted!", e);
                // clean up whatever needs to be handled before interrupting
                Thread.currentThread().interrupt();
            }
        }
    }

    // main process = main thread
    public static void main(String[] args) {
        Worker worker = new Worker();

        /* Description of the following methods that CompletableFuture class has
         *  - supplyAsync(): executing task on new thread(child)
         *  - thenComposeAsync(): executing method(task) specified result of before stage as its argument
         *  - thenApplyAsync(): executing method(task) specified result of before stage as its argument
         *  - whenCompleteAsync: executing method(task) specified result and exception of before stage as its argument
         * 
         * thenComposeAsync vs thenApplyAsync
         *      whether creating CompletableFuture object
         *      thenApplySync method rapped result to exist CompeletableFuture object
         *      thenComposeAsync method creates new CompletableFuture object, and raps result to the object
         *      [common] specified method (task) will be executed on new thread
         */
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(worker::exec)
                                            .thenComposeAsync(s -> CompletableFuture.supplyAsync(() -> s+" world!"))
                                            .thenApplyAsync(worker::aggregate)
                                            .whenCompleteAsync(worker::show);
        future.join();
    }
}
