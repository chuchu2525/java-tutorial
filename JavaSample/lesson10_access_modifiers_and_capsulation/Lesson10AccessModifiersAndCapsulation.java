package JavaSample.lesson10_access_modifiers_and_capsulation;

public class Lesson10AccessModifiersAndCapsulation {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("Yuu", 1000);

        account.deposit(500);

        System.out.println("口座名義: " + account.getOwner());
        System.out.println("残高: " + account.getBalance());
    }
}

class BankAccount {
    private String owner;
    private int balance;

    BankAccount(String owner, int balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}
