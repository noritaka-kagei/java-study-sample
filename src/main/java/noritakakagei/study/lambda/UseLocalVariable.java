package noritakakagei.study.lambda;

import java.util.function.Consumer;

class UseLocalVariable {
    Consumer<String> createProcess(String param) {
        String local = "local";
        return str -> System.out.println(str + ": " + param + " in " + local);
    }

    public static void main(String... args) {
        UseLocalVariable main = new UseLocalVariable();

        // create lambda function
        Consumer<String> process = main.createProcess("parameter");

        // execute lambda
        process.accept("showing");
    }
}