package JavaSample.lesson03_loops;

public class Lesson03Loops {
    public static void main(String[] args) {
        System.out.println("for文");
        for (int i = 1; i <= 3; i++) {
            System.out.println(i + "回目");
        }

        System.out.println("while文");
        int count = 1;
        while (count <= 3) {
            System.out.println(count + "回目");
            count++;
        }
    }
}
