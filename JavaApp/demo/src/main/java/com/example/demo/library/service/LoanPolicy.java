package com.example.demo.library.service;

import com.example.demo.library.model.Book;
import com.example.demo.library.model.Loan;
import com.example.demo.library.model.Member;
import org.springframework.stereotype.Component;

@Component
public class LoanPolicy {
    private static final int MAX_BOOKS_PER_MEMBER = 2;

    public String validateBorrow(Member member, Book book, Loan unreturnedLoan, long unreturnedLoanCount) {
        if (member == null) {
            return "会員が見つかりません";
        }
        if (book == null) {
            return "本が見つかりません";
        }
        if (unreturnedLoan != null) {
            return "その本はすでに貸出中です";
        }
        if (unreturnedLoanCount >= MAX_BOOKS_PER_MEMBER) {
            return "貸出上限に達しているため借りられません";
        }
        return "OK";
    }
}
