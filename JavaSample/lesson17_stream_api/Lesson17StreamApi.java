package JavaSample.lesson17_stream_api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lesson17StreamApi {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> doubledEvenNumbers = numbers.stream()
                .filter(number -> number % 2 == 0)
                .map(number -> number * 2)
                .collect(Collectors.toList());

        System.out.println("元の一覧: " + numbers);
        System.out.println("偶数を2倍した一覧: " + doubledEvenNumbers);
    }
}
