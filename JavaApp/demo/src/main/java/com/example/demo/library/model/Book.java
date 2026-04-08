package com.example.demo.library.model;

public class Book {
    private final String id;
    private final String title;
    private final String author;
    private boolean borrowed;
    private String borrowedByMemberId;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public boolean isAvailable() {
        return !borrowed;
    }

    public void borrow(String memberId) {
        borrowed = true;
        borrowedByMemberId = memberId;
    }

    public void giveBack() {
        borrowed = false;
        borrowedByMemberId = null;
    }

    public boolean isBorrowedBy(String memberId) {
        return borrowed && memberId.equals(borrowedByMemberId);
    }
}
