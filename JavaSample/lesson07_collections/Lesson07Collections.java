package JavaSample.lesson07_collections;

import java.util.ArrayList;

public class Lesson07Collections {
    public static void main(String[] args) {
        ArrayList<String> subjects = new ArrayList<>();

        subjects.add("Java");
        subjects.add("SQL");
        subjects.add("HTML");

        System.out.println("最初の要素: " + subjects.get(0));

        for (String subject : subjects) {
            System.out.println(subject);
        }
    }
}
