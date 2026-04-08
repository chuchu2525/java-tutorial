package JavaSample.lesson14_generics_and_collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Lesson14GenericsAndCollections {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Yuu");
        names.add("Aki");

        Set<Integer> ids = new HashSet<>();
        ids.add(101);
        ids.add(102);
        ids.add(101);

        Map<String, Integer> scores = new HashMap<>();
        scores.put("Math", 90);
        scores.put("English", 85);

        System.out.println("名前一覧: " + names);
        System.out.println("ID一覧: " + ids);
        System.out.println("Math の点数: " + scores.get("Math"));
    }
}
