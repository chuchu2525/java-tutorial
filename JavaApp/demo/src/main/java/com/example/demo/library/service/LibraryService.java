package com.example.demo.library.service;

import com.example.demo.library.dto.ActionResponse;
import com.example.demo.library.model.Book;
import com.example.demo.library.model.Loan;
import com.example.demo.library.model.Member;
import com.example.demo.library.repository.LibraryRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final LoanPolicy loanPolicy;

    public LibraryService(LibraryRepository libraryRepository, LoanPolicy loanPolicy) {
        this.libraryRepository = libraryRepository;
        this.loanPolicy = loanPolicy;
    }

    @Transactional(readOnly = true)
    public List<Book> getBooks() {
        return libraryRepository.findAllBooks();
    }

    @Transactional(readOnly = true)
    public List<Book> getAvailableBooks() {
        Set<String> unreturnedBookIds = getUnreturnedBookIds();
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : libraryRepository.findAllBooks()) {
            if (!unreturnedBookIds.contains(book.getId())) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    @Transactional(readOnly = true)
    public List<Book> getBorrowedBooks() {
        Set<String> unreturnedBookIds = getUnreturnedBookIds();
        List<Book> borrowedBooks = new ArrayList<>();
        for (Book book : libraryRepository.findAllBooks()) {
            if (unreturnedBookIds.contains(book.getId())) {
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }

    @Transactional(readOnly = true)
    public List<Member> getMembers() {
        return libraryRepository.findAllMembers();
    }

    @Transactional(readOnly = true)
    public List<Loan> getActiveLoans() {
        return libraryRepository.findUnreturnedLoans();
    }

    @Transactional(readOnly = true)
    public int getBorrowedBookCount(String memberId) {
        return Math.toIntExact(libraryRepository.countUnreturnedLoansByMemberId(memberId));
    }

    @Transactional(readOnly = true)
    public boolean isBookBorrowed(String bookId) {
        return libraryRepository.findUnreturnedLoanByBookId(bookId) != null;
    }

    @Transactional
    public ActionResponse borrowBook(String memberId, String bookId) {
        Member member = libraryRepository.findMemberById(memberId);
        Book book = libraryRepository.findBookById(bookId);
        Loan unreturnedLoan = libraryRepository.findUnreturnedLoanByBookId(bookId);
        long unreturnedLoanCount = libraryRepository.countUnreturnedLoansByMemberId(memberId);
        String validationResult = loanPolicy.validateBorrow(member, book, unreturnedLoan, unreturnedLoanCount);

        if (!"OK".equals(validationResult)) {
            return new ActionResponse(false, validationResult);
        }

        libraryRepository.createLoan(book, member, LocalDate.now());

        return new ActionResponse(true, member.getName() + " が " + book.getTitle() + " を借りました");
    }

    @Transactional
    public ActionResponse returnBook(String memberId, String bookId) {
        Member member = libraryRepository.findMemberById(memberId);
        Book book = libraryRepository.findBookById(bookId);

        if (member == null || book == null) {
            return new ActionResponse(false, "返却対象が見つかりません");
        }

        Loan unreturnedLoan = libraryRepository.findUnreturnedLoan(bookId, memberId);
        if (unreturnedLoan == null) {
            return new ActionResponse(false, "その会員はこの本を借りていません");
        }

        unreturnedLoan.markReturned(LocalDate.now());
        libraryRepository.saveLoan(unreturnedLoan);

        return new ActionResponse(true, member.getName() + " が " + book.getTitle() + " を返却しました");
    }

    @Transactional
    public ActionResponse returnBookByBookId(String bookId) {
        Loan unreturnedLoan = libraryRepository.findUnreturnedLoanByBookId(bookId);
        if (unreturnedLoan == null) {
            return new ActionResponse(false, "貸出中の本が見つかりません");
        }
        return returnBook(unreturnedLoan.getMemberId(), bookId);
    }

    @Transactional
    public ActionResponse addBook(String title, String author) {
        String normalizedTitle = normalize(title);
        String normalizedAuthor = normalize(author);

        if (normalizedTitle.isEmpty()) {
            return new ActionResponse(false, "本のタイトルを入力してください");
        }
        if (normalizedAuthor.isEmpty()) {
            return new ActionResponse(false, "著者名を入力してください");
        }

        Book book = libraryRepository.saveBook(normalizedTitle, normalizedAuthor);
        return new ActionResponse(true, book.getTitle() + " を追加しました");
    }

    @Transactional
    public ActionResponse addMember(String name) {
        String normalizedName = normalize(name);
        if (normalizedName.isEmpty()) {
            return new ActionResponse(false, "会員名を入力してください");
        }

        Member member = libraryRepository.saveMember(normalizedName);
        return new ActionResponse(true, member.getName() + " を会員登録しました");
    }

    private Set<String> getUnreturnedBookIds() {
        Set<String> bookIds = new HashSet<>();
        for (Loan loan : libraryRepository.findUnreturnedLoans()) {
            bookIds.add(loan.getBookId());
        }
        return bookIds;
    }

    private String normalize(String value) {
        if (value == null) {
            return "";
        }
        return value.trim();
    }
}
