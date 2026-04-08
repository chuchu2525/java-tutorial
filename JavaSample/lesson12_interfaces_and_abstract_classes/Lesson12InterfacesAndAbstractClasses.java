package JavaSample.lesson12_interfaces_and_abstract_classes;

public class Lesson12InterfacesAndAbstractClasses {
    public static void main(String[] args) {
        Phone phone = new Phone("MyPhone");
        Device device = phone;

        device.powerOn();
        device.run();
        phone.greet();
    }
}

interface Greeter {
    void greet();
}

abstract class Device {
    protected String name;

    Device(String name) {
        this.name = name;
    }

    public void powerOn() {
        System.out.println(name + " の電源を入れました");
    }

    public abstract void run();
}

class Phone extends Device implements Greeter {
    Phone(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(name + " でアプリを起動します");
    }

    @Override
    public void greet() {
        System.out.println("こんにちは、" + name + " です");
    }
}
