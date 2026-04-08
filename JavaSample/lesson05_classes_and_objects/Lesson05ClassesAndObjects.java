package JavaSample.lesson05_classes_and_objects;

class Person {
    String name;
    int age;

    void introduce() {
        System.out.println("私は " + name + "、" + age + "歳です。");
    }
}

public class Lesson05ClassesAndObjects {
    public static void main(String[] args) {
        Person person = new Person();
        person.name = "Yuu";
        person.age = 20;
        person.introduce();
    }
}
