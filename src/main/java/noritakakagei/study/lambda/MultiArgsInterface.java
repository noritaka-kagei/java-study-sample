package noritakakagei.study.lambda;

import java.util.function.Function;

class MultiArgsInterface {
    public static void main(String... args) {
        // 2 arguments of function interface
        Function<Integer, Function<Integer, Integer>> plus2 = a -> b -> a + b;
        int result = plus2.apply(3).apply(10);    // 3 + 10 = 13
        System.out.println("Result: " + result);

        // 3 arguments of function interface
        Function<Integer, Function<Integer, Function<Integer, Integer>>> plus3 = a -> b -> c -> a + b + c;
        result = plus3.apply(10).apply(100).apply(1000);  // 10 + 100 + 1000 = 1110
        System.out.println("Result: " + result);
    }
}