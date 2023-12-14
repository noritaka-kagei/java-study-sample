package noritakakagei.study.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

class ListIteratorSample {
    private List<Integer> list = new ArrayList<>(); // 1~10
    
    public void init() {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
    }

    public void addDouble2Next() {
        ListIterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            iterator.add(value * 2);
        }
    }

    public void printAllAsc() {
        // Print in ascending order
        for (Integer value : list) {
            System.out.println(value);
        }
    }

    public void printAllDesc() {
        // Print in descending order
        ListIterator<Integer> iterator = list.listIterator(list.size());
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
    }

    // main method
    public static void main(String[] args) {
        ListIteratorSample obj = new ListIteratorSample();
        obj.init();
        obj.addDouble2Next();
        obj.printAllAsc();
        obj.printAllDesc();
    }
}