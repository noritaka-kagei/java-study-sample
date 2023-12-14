package noritakakagei.study.collections;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class SetAddItr {
    private Set<Integer> set = new HashSet<>(); // 1~10

    public void init() {
        for (int i = 1; i <= 10; i++) {
            set.add(i);
        }
    }

    public void doubleSet() {
        Set<Integer> result = new HashSet<>();
        
        for (Integer value : set) {
            result.add(value*2);
        }

        set = result;
    }

    public void printAllRandom() {
        for (Iterator<Integer> iterator = set.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
    }

    // main method
    public static void main(String[] args) {
        SetAddItr obj = new SetAddItr();

        obj.init();
        obj.doubleSet();
        obj.printAllRandom();
    }
}