package noritakakagei.study.virtualthread;

import static java.util.stream.IntStream.range;
import java.time.Duration;

public class IntroVirtualThread {
    public static void main(String... args) throws InterruptedException {
        var vThread1 = Thread.ofVirtual().name("仮想スレッド1").unstarted(() -> {
            range(0, 1000).forEach(n -> {
                System.out.printf("%s, %d\n", Thread.currentThread().getName(), n);
            });
        });

        var vThread2 = Thread.ofVirtual().name("仮想スレッド2").unstarted(() -> {
            range(0, 1000).forEach(n -> {
                System.out.printf("%s, %d\n", Thread.currentThread().getName(), n);
            });
        });

        // サブスレッド(仮想スレッド)の実行
        vThread1.start();
        vThread2.start();

        // メインスレッド上での実行
        range(0, 1000).forEach(n -> {
            System.out.printf("メインスレッド, %d\n", n);
        });

        // サブスレッド終了を待機（引数でタイムアウト値を指定（10秒間））
        vThread1.join(Duration.ofSeconds(10));
        vThread2.join(Duration.ofSeconds(10));
    }
}