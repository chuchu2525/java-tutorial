package JavaSample.lesson11_inheritance_and_polymorphism;

public class Lesson11InheritanceAndPolymorphism {
    public static void main(String[] args) {
        Animal animal = new Dog("Pochi");

        animal.introduce();
        animal.speak();
    }
}

class Animal {
    protected String name;

    Animal(String name) {
        this.name = name;
    }

    public void introduce() {
        System.out.println("名前は " + name + " です");
    }

    public void speak() {
        System.out.println("何かの鳴き声です");
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);
    }

    @Override
    public void speak() {
        System.out.println(name + " はワンワンと鳴きます");
    }
}
