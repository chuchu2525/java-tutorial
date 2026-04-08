package JavaSample.lesson18_optional_and_null_handling;

import java.util.Optional;

public class Lesson18OptionalAndNullHandling {
    public static void main(String[] args) {
        Optional<String> nickname1 = findNickname("Yuu");
        Optional<String> nickname2 = findNickname("Aki");

        System.out.println("Yuu のニックネーム: " + nickname1.orElse("未設定"));
        System.out.println("Aki のニックネーム: " + nickname2.orElse("未設定"));
    }

    public static Optional<String> findNickname(String name) {
        if ("Yuu".equals(name)) {
            return Optional.of("Yu");
        }
        return Optional.empty();
    }
}
