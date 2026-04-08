package JavaSample.lesson08_exceptions;

public class Lesson08Exceptions {
    public static void main(String[] args) {
        try {
            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("例外が発生しました: " + e.getMessage());
        }
    }
}
