package JavaSample.lesson04_methods;

public class Lesson04Methods {
    public static void main(String[] args) {
        greet("Yuu");

        int total = add(3, 5);
        System.out.println("3 + 5 = " + total);
    }

    public static void greet(String name) {
        System.out.println("こんにちは、" + name + "さん");
    }

    public static int add(int a, int b) {
        return a + b;
    }
}
