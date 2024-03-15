package noritakakagei.study.virtualthread;

public class ThreadSample {
    public static void main(String... args) {
        // Pattern A
        Thread vThread = Thread.ofVirtual().unstarted(() -> System.out.println("仮想スレッドから呼ばれる処理"));
        vThread.start();

        // Pattern B
        Thread.ofVirtual().start(() -> System.out.println("仮想スレッドから呼ばれる処理"));
    }
}


// リストX.X　仮想スレッドに処理をさせる例
// jshell> Thread.ofVirtual().start(() -> System.out.println("仮想スレッドから呼ばれる処理"));
// 仮想スレッドから呼ばれる処理
