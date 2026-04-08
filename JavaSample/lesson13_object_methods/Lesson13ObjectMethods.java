package JavaSample.lesson13_object_methods;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Lesson13ObjectMethods {
    public static void main(String[] args) {
        Book book1 = new Book("Java 入門", 2000);
        Book book2 = new Book("Java 入門", 2000);

        System.out.println(book1);
        System.out.println("equals の結果: " + book1.equals(book2));

        Set<Book> books = new HashSet<>();
        books.add(book1);
        books.add(book2);

        System.out.println("Set の件数: " + books.size());
    }
}

class Book {
    private String title;
    private int price;

    Book(String title, int price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{title='" + title + "', price=" + price + "}";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Book)) {
            return false;
        }
        Book book = (Book) other;
        return price == book.price && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price);
    }
}
