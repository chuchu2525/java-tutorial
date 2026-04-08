package JavaSample.lesson01_variables;

public class Lesson01Variables {
    public static void main(String[] args) {
        String name = "Yuu";
        int age = 20;
        double height = 170.5;
        boolean likesJava = true;

        System.out.println("名前: " + name);
        System.out.println("年齢: " + age);
        System.out.println("身長: " + height);
        System.out.println("Javaは好き? " + likesJava);

        age = age + 1;
        System.out.println("来年の年齢: " + age);
    }
}
