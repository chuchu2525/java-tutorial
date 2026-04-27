package com.example.demo.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private LocalDate loanDate;
    private LocalDate returnedDate;

    protected Loan() {
    }

    public Loan(Book book, Member member, LocalDate loanDate) {
        this.book = book;
        this.member = member;
        this.loanDate = loanDate;
    }

    public Long getId() {
        return id;
    }

    @JsonIgnore
    public Book getBook() {
        return book;
    }

    @JsonIgnore
    public Member getMember() {
        return member;
    }

    public String getBookId() {
        return book.getId();
    }

    public String getMemberId() {
        return member.getId();
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public boolean isUnreturned() {
        return returnedDate == null;
    }

    public void markReturned(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }
}
