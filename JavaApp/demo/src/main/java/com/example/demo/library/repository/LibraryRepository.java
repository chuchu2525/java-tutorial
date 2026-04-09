package com.example.demo.library.repository;

import com.example.demo.library.model.Book;
import com.example.demo.library.model.Loan;
import com.example.demo.library.model.Member;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class LibraryRepository {
    private final Map<String, Book> books = new LinkedHashMap<>();
    private final Map<String, Member> members = new LinkedHashMap<>();
    private final List<Loan> loans = new ArrayList<>();
    private int nextBookNumber = 4;
    private int nextMemberNumber = 3;

    public LibraryRepository() {
        books.put("b1", new Book("b1", "Java入門", "Yamada"));
        books.put("b2", new Book("b2", "Spring Boot入門", "Suzuki"));
        books.put("b3", new Book("b3", "設計の基本", "Tanaka"));

        members.put("m1", new Member("m1", "Yuu"));
        members.put("m2", new Member("m2", "Aoi"));
    }

    public List<Book> findAllBooks() {
        return new ArrayList<>(books.values());
    }

    public List<Member> findAllMembers() {
        return new ArrayList<>(members.values());
    }

    public List<Loan> findAllLoans() {
        return new ArrayList<>(loans);
    }

    public Book findBookById(String bookId) {
        return books.get(bookId);
    }

    public Member findMemberById(String memberId) {
        return members.get(memberId);
    }

    public void saveLoan(Loan loan) {
        loans.add(loan);
    }

    public Book saveBook(String title, String author) {
        String bookId = "b" + nextBookNumber++;
        Book book = new Book(bookId, title, author);
        books.put(bookId, book);
        return book;
    }

    public Member saveMember(String name) {
        String memberId = "m" + nextMemberNumber++;
        Member member = new Member(memberId, name);
        members.put(memberId, member);
        return member;
    }

    public Loan findActiveLoan(String bookId, String memberId) {
        for (Loan loan : loans) {
            if (loan.isActive() && loan.getBookId().equals(bookId) && loan.getMemberId().equals(memberId)) {
                return loan;
            }
        }
        return null;
    }

    public Loan findActiveLoanByBookId(String bookId) {
        for (Loan loan : loans) {
            if (loan.isActive() && loan.getBookId().equals(bookId)) {
                return loan;
            }
        }
        return null;
    }
}
