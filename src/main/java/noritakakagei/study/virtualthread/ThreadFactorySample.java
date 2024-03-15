package noritakakagei.study.virtualthread;

import java.util.concurrent.ThreadFactory;

public class ThreadFactorySample {
    public static void main(String... args) {
        ThreadFactory factory = Thread.ofVirtual().name("仮想スレッド").factory();
        for(int i=0; i<5; i++) {
            Thread vThread = factory.newThread(() -> System.out.println("仮想スレッドから呼び出される処理"));
            vThread.start();
        }
    }
}


// リストX.X　5つの仮想スレッドを生成し実行させる例
// jshell> ThreadFactory factory = Thread.ofVirtual().factory();
// jshell> for(int i=0; i<5; i++) {
// jshell>     Thread vThread = factory.newThread(() -> System.out.println("仮想スレッドから呼び出される処理"));
// jshell>     vThread.start();
// jshell> }
// 仮想スレッドから呼ばれる処理　(5回出力される)
