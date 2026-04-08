package com.example.demo.library.model;

import java.time.LocalDate;

public class Loan {
    private final String bookId;
    private final String memberId;
    private final LocalDate loanDate;
    private boolean active = true;

    public Loan(String bookId, String memberId, LocalDate loanDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.loanDate = loanDate;
    }

    public String getBookId() {
        return bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public boolean isActive() {
        return active;
    }

    public void markReturned() {
        active = false;
    }
}
