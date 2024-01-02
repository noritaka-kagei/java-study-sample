package noritakakagei.study.lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

class Main {
    // composition of standard functional interface
    public static void main(String... args) {
        /* Function<T, R> */
        // convert 2 functions
        Function<String, Integer> getLength = String::length;
        Function<Integer, String> toHex = i -> Integer.toString(i, 16);

        Function<String, String> newFn = getLength.andThen(toHex);
        System.out.println(newFn.apply("0123456789"));

        // covert(repeat) functions 3 times
        Function<Integer, Integer> increment = i -> i + 1;
        
        Function<Integer, Integer> increment3 = increment.andThen(increment).andThen(increment);
        System.out.println(increment3.apply(5));

        /* Consumer<T> */
        Consumer<String> process1 = msg -> System.out.println("showing[1]: " + msg);
        Consumer<String> process2 = msg -> System.out.println("showing[2]: " + msg);

        Consumer<String> process = process1.andThen(process2);
        process.accept("sample code of consumer function");

        /* Predicate<T> */
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isNegative = isPositive.negate();

        System.out.println("-1 is negative: " + isNegative.test(-1));

        Predicate<Integer> isPositiveEven = isPositive.and(isEven);
        System.out.println("10 is positive and even: " + isPositiveEven.test(10));
    }
}