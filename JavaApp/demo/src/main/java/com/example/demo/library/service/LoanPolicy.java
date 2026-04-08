package com.example.demo.library.service;

import com.example.demo.library.model.Book;
import com.example.demo.library.model.Member;
import org.springframework.stereotype.Component;

@Component
public class LoanPolicy {
    private static final int MAX_BOOKS_PER_MEMBER = 2;

    public String validateBorrow(Member member, Book book) {
        if (member == null) {
            return "会員が見つかりません";
        }
        if (book == null) {
            return "本が見つかりません";
        }
        if (!book.isAvailable()) {
            return "その本はすでに貸出中です";
        }
        if (member.getBorrowedBookCount() >= MAX_BOOKS_PER_MEMBER) {
            return "貸出上限に達しているため借りられません";
        }
        return "OK";
    }
}
