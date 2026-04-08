package com.example.demo.library.service;

import com.example.demo.library.dto.ActionResponse;
import com.example.demo.library.model.Book;
import com.example.demo.library.model.Loan;
import com.example.demo.library.model.Member;
import com.example.demo.library.repository.LibraryRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final LoanPolicy loanPolicy;

    public LibraryService(LibraryRepository libraryRepository, LoanPolicy loanPolicy) {
        this.libraryRepository = libraryRepository;
        this.loanPolicy = loanPolicy;
    }

    public List<Book> getBooks() {
        return libraryRepository.findAllBooks();
    }

    public List<Member> getMembers() {
        return libraryRepository.findAllMembers();
    }

    public List<Loan> getActiveLoans() {
        List<Loan> activeLoans = new ArrayList<>();
        for (Loan loan : libraryRepository.findAllLoans()) {
            if (loan.isActive()) {
                activeLoans.add(loan);
            }
        }
        return activeLoans;
    }

    public ActionResponse borrowBook(String memberId, String bookId) {
        Member member = libraryRepository.findMemberById(memberId);
        Book book = libraryRepository.findBookById(bookId);
        String validationResult = loanPolicy.validateBorrow(member, book);

        if (!"OK".equals(validationResult)) {
            return new ActionResponse(false, validationResult);
        }

        book.borrow(memberId);
        member.borrowBook(bookId);
        libraryRepository.saveLoan(new Loan(bookId, memberId, LocalDate.now()));

        return new ActionResponse(true, member.getName() + " が " + book.getTitle() + " を借りました");
    }

    public ActionResponse returnBook(String memberId, String bookId) {
        Member member = libraryRepository.findMemberById(memberId);
        Book book = libraryRepository.findBookById(bookId);

        if (member == null || book == null) {
            return new ActionResponse(false, "返却対象が見つかりません");
        }
        if (!book.isBorrowedBy(memberId) || !member.hasBook(bookId)) {
            return new ActionResponse(false, "その会員はこの本を借りていません");
        }

        book.giveBack();
        member.returnBook(bookId);

        Loan activeLoan = libraryRepository.findActiveLoan(bookId, memberId);
        if (activeLoan != null) {
            activeLoan.markReturned();
        }

        return new ActionResponse(true, member.getName() + " が " + book.getTitle() + " を返却しました");
    }
}
