package noritakakagei.study.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/* Stream Processing consists of 3 procedures
 * 1. generate Stream object
 * 2. execute multi-middle processes
 * 3. execute last(terminate) process
 */
class StandardProcedure {
    public static void main(String... args) {
        List<String> list = Arrays.asList("noritaka", "kagei", "is", "developer");

        // 1. generate Stream object
        Stream<String> stream = list.stream();

        // 2. execute multi-middle processes
        Stream<String> stream2 = stream.filter(s -> s.contains("e"));
        Stream<String> stream3 = stream2.map(String::toUpperCase);

        // 3. execute last(terminate) process
        stream3.forEach(System.out::println);

        /* refactoring above processes */
        list.stream()
            .filter(s -> s.contains("e"))
            .map(String::toUpperCase)
            .forEach(System.out::println);
    }
}