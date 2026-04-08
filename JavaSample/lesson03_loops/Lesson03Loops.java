package JavaSample.lesson03_loops;

public class Lesson03Loops {
    public static void main(String[] args) {
        System.out.println("=== for文: 回数が決まっている繰り返し ===");
        for (int i = 1; i <= 5; i++) {
            System.out.println("for -> " + i);
        }

        System.out.println();
        System.out.println("=== while文: 条件を満たす間の繰り返し ===");
        int sum = 0;
        int n = 1;
        while (sum <= 20) {
            sum += n;
            System.out.println("while -> n=" + n + ", sum=" + sum);
            n++;
        }

        System.out.println("終了時点: n=" + n + ", sum=" + sum);
    }
}
