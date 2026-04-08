package JavaSample.lesson16_lambda_and_functional_interfaces;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Lesson16LambdaAndFunctionalInterfaces {
    public static void main(String[] args) {
        Predicate<String> isLongName = name -> name.length() >= 4;
        Consumer<String> printer = text -> System.out.println("出力: " + text);

        String name = "Yuu";

        System.out.println("4文字以上か: " + isLongName.test(name));
        printer.accept(name);
    }
}
