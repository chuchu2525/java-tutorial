package com.example.demo.library.model;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private final String id;
    private final String name;
    private final List<String> borrowedBookIds = new ArrayList<>();

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBorrowedBookCount() {
        return borrowedBookIds.size();
    }

    public boolean hasBook(String bookId) {
        return borrowedBookIds.contains(bookId);
    }

    public void borrowBook(String bookId) {
        borrowedBookIds.add(bookId);
    }

    public void returnBook(String bookId) {
        borrowedBookIds.remove(bookId);
    }
}
