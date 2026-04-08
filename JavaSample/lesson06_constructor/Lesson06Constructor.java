package JavaSample.lesson06_constructor;

class Student {
    String name;
    int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    void showProfile() {
        System.out.println("名前: " + name + ", 学年: " + grade);
    }
}

public class Lesson06Constructor {
    public static void main(String[] args) {
        Student student = new Student("Sato", 2);
        student.showProfile();
    }
}
