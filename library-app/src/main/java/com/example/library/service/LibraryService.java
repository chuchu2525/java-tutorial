package com.example.library.service;

import com.example.library.domain.Book;
import com.example.library.domain.Loan;
import com.example.library.domain.Member;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LoanRepository;
import com.example.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LoanRepository loanRepository;

    @Transactional
    public Loan borrowBook(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is already borrowed");
        }

        List<Loan> activeLoans = loanRepository.findByMemberAndReturnDateIsNull(member);
        if (activeLoans.size() >= member.getMaxBorrowingLimit()) {
            throw new IllegalStateException("Member has reached the borrowing limit");
        }

        book.setAvailable(false);
        bookRepository.save(book);

        Loan loan = new Loan(book, member);
        return loanRepository.save(loan);
    }

    @Transactional
    public void returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        if (loan.getReturnDate() != null) {
            throw new IllegalStateException("Book already returned");
        }

        loan.setReturnDate(LocalDate.now());
        loan.getBook().setAvailable(true);

        loanRepository.save(loan);
        bookRepository.save(loan.getBook());
    }
}
