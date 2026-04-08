package JavaSample.lesson09_arrays_and_switch;

public class Lesson09ArraysAndSwitch {
    public static void main(String[] args) {
        String[] colors = {"red", "blue", "green"};

        System.out.println("最初の色: " + colors[0]);
        System.out.println("配列の長さ: " + colors.length);

        for (String color : colors) {
            System.out.println("色: " + color);
        }

        int menu = 2;
        switch (menu) {
            case 1:
                System.out.println("コーヒーを選びました");
                break;
            case 2:
                System.out.println("紅茶を選びました");
                break;
            default:
                System.out.println("その他の飲み物を選びました");
                break;
        }
    }
}
