package JavaSample.lesson02_conditions;

public class Lesson02Conditions {
    public static void main(String[] args) {
        int score = 0;

        if (score >= 80) {
            System.out.println("良い点です");
        } else if (score >= 60) {
            System.out.println("合格です");
        } else {
            System.out.println("もう少し頑張りましょう");
        }

        String message = (score >= 60) ? "合格" : "不合格";
        System.out.println("判定: " + message);
    }
}
