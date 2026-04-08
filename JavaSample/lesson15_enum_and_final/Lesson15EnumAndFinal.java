package JavaSample.lesson15_enum_and_final;

public class Lesson15EnumAndFinal {
    public static void main(String[] args) {
        final int maxRetry = 3;
        Status status = Status.DOING;

        System.out.println("現在の状態: " + status);
        System.out.println("最大再試行回数: " + maxRetry);
        System.out.println("消費税率: " + Constants.TAX_RATE);
    }
}

enum Status {
    TODO,
    DOING,
    DONE
}

final class Constants {
    public static final double TAX_RATE = 0.1;

    private Constants() {
    }
}
