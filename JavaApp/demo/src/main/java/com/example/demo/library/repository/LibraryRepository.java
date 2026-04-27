package com.example.demo.library.repository;

import com.example.demo.library.model.Book;
import com.example.demo.library.model.Loan;
import com.example.demo.library.model.Member;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class LibraryRepository {
    private final BookJpaRepository bookJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final LoanJpaRepository loanJpaRepository;

    public LibraryRepository(BookJpaRepository bookJpaRepository,
                             MemberJpaRepository memberJpaRepository,
                             LoanJpaRepository loanJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
        this.memberJpaRepository = memberJpaRepository;
        this.loanJpaRepository = loanJpaRepository;
    }

    public List<Book> findAllBooks() {
        return bookJpaRepository.findAll();
    }

    public List<Member> findAllMembers() {
        return memberJpaRepository.findAll();
    }

    public List<Loan> findAllLoans() {
        return loanJpaRepository.findAll();
    }

    public List<Loan> findUnreturnedLoans() {
        return loanJpaRepository.findByReturnedDateIsNull();
    }

    public Book findBookById(String bookId) {
        return bookJpaRepository.findById(bookId).orElse(null);
    }

    public Member findMemberById(String memberId) {
        return memberJpaRepository.findById(memberId).orElse(null);
    }

    public Loan saveLoan(Loan loan) {
        return loanJpaRepository.save(loan);
    }

    public Book saveBook(String title, String author) {
        Book book = new Book(nextBookId(), title, author);
        return bookJpaRepository.save(book);
    }

    public Member saveMember(String name) {
        Member member = new Member(nextMemberId(), name);
        return memberJpaRepository.save(member);
    }

    public Loan findUnreturnedLoan(String bookId, String memberId) {
        return loanJpaRepository.findByBook_IdAndMember_IdAndReturnedDateIsNull(bookId, memberId).orElse(null);
    }

    public Loan findUnreturnedLoanByBookId(String bookId) {
        return loanJpaRepository.findByBook_IdAndReturnedDateIsNull(bookId).orElse(null);
    }

    public long countUnreturnedLoansByMemberId(String memberId) {
        return loanJpaRepository.countByMember_IdAndReturnedDateIsNull(memberId);
    }

    public Loan createLoan(Book book, Member member, LocalDate loanDate) {
        return loanJpaRepository.save(new Loan(book, member, loanDate));
    }

    public boolean hasAnyBooks() {
        return bookJpaRepository.count() > 0;
    }

    public boolean hasAnyMembers() {
        return memberJpaRepository.count() > 0;
    }

    public void saveInitialBooks(List<Book> books) {
        bookJpaRepository.saveAll(books);
    }

    public void saveInitialMembers(List<Member> members) {
        memberJpaRepository.saveAll(members);
    }

    private String nextBookId() {
        return "b" + nextNumber(findAllBooks().stream().map(Book::getId).toList(), "b");
    }

    private String nextMemberId() {
        return "m" + nextNumber(findAllMembers().stream().map(Member::getId).toList(), "m");
    }

    private int nextNumber(List<String> ids, String prefix) {
        return ids.stream()
                .filter(id -> id != null && id.startsWith(prefix))
                .map(id -> id.substring(prefix.length()))
                .map(this::parseNumberOrNull)
                .filter(number -> number != null)
                .max(Comparator.naturalOrder())
                .map(number -> number + 1)
                .orElse(1);
    }

    private Integer parseNumberOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
