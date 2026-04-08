package JavaSample.lesson21_oop_library_demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lesson21OopLibraryDemo {
    public static void main(String[] args) {
        LoanPolicy loanPolicy = new LoanPolicy(2);
        LibraryService libraryService = new LibraryService(loanPolicy);

        libraryService.registerBook(new Book("b1", "Java入門", "Yamada"));
        libraryService.registerBook(new Book("b2", "Spring Boot入門", "Suzuki"));
        libraryService.registerBook(new Book("b3", "設計の基本", "Tanaka"));

        libraryService.registerMember(new Member("m1", "Yuu"));
        libraryService.registerMember(new Member("m2", "Aoi"));

        printHeader("初期状態");
        printBooks(libraryService.getBooks());

        printHeader("Yuu が Java入門 を借りる");
        System.out.println(libraryService.borrowBook("m1", "b1"));

        printHeader("Aoi が同じ本を借りようとする");
        System.out.println(libraryService.borrowBook("m2", "b1"));

        printHeader("Yuu が 2 冊目を借りる");
        System.out.println(libraryService.borrowBook("m1", "b2"));

        printHeader("Yuu が上限を超えて 3 冊目を借りようとする");
        System.out.println(libraryService.borrowBook("m1", "b3"));

        printHeader("Yuu が Java入門 を返却する");
        System.out.println(libraryService.returnBook("m1", "b1"));

        printHeader("返却後に Yuu が 3 冊目を借りる");
        System.out.println(libraryService.borrowBook("m1", "b3"));

        printHeader("最終状態");
        printBooks(libraryService.getBooks());
        printMembers(libraryService.getMembers());
        printLoans(libraryService.getActiveLoans());
    }

    private static void printHeader(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }

    private static void printBooks(List<Book> books) {
        for (Book book : books) {
            System.out.println(book.getTitle() + " / 貸出中: " + book.isBorrowed());
        }
    }

    private static void printMembers(List<Member> members) {
        for (Member member : members) {
            System.out.println(member.getName() + " / 借りている冊数: " + member.getBorrowedBookCount());
        }
    }

    private static void printLoans(List<Loan> loans) {
        for (Loan loan : loans) {
            System.out.println(loan.getMemberId() + " -> " + loan.getBookId() + " / " + loan.getLoanDate());
        }
    }
}

class Book {
    private final String id;
    private final String title;
    private final String author;
    private boolean borrowed;
    private String borrowedByMemberId;

    Book(String id, String title, String author) {
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

class Member {
    private final String id;
    private final String name;
    private final List<String> borrowedBookIds = new ArrayList<>();

    Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBorrowedBookCount() {
        return borrowedBookIds.size();
    }

    public boolean hasBook(String bookId) {
        return borrowedBookIds.contains(bookId);
    }

    public void borrowBook(String bookId) {
        borrowedBookIds.add(bookId);
    }

    public void returnBook(String bookId) {
        borrowedBookIds.remove(bookId);
    }
}

class Loan {
    private final String bookId;
    private final String memberId;
    private final LocalDate loanDate;
    private boolean active = true;

    Loan(String bookId, String memberId, LocalDate loanDate) {
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

class LoanPolicy {
    private final int maxBooksPerMember;

    LoanPolicy(int maxBooksPerMember) {
        this.maxBooksPerMember = maxBooksPerMember;
    }

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
        if (member.getBorrowedBookCount() >= maxBooksPerMember) {
            return "貸出上限に達しているため借りられません";
        }
        return "OK";
    }
}

class LibraryService {
    private final LoanPolicy loanPolicy;
    private final List<Book> books = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<Loan> loans = new ArrayList<>();

    LibraryService(LoanPolicy loanPolicy) {
        this.loanPolicy = loanPolicy;
    }

    public void registerBook(Book book) {
        books.add(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    public String borrowBook(String memberId, String bookId) {
        Member member = findMember(memberId);
        Book book = findBook(bookId);
        String validationResult = loanPolicy.validateBorrow(member, book);

        if (!"OK".equals(validationResult)) {
            return validationResult;
        }

        book.borrow(memberId);
        member.borrowBook(bookId);
        loans.add(new Loan(bookId, memberId, LocalDate.now()));
        return member.getName() + " が " + book.getTitle() + " を借りました";
    }

    public String returnBook(String memberId, String bookId) {
        Member member = findMember(memberId);
        Book book = findBook(bookId);

        if (member == null || book == null) {
            return "返却対象が見つかりません";
        }
        if (!book.isBorrowedBy(memberId) || !member.hasBook(bookId)) {
            return "その会員はこの本を借りていません";
        }

        book.giveBack();
        member.returnBook(bookId);

        Loan activeLoan = findActiveLoan(bookId, memberId);
        if (activeLoan != null) {
            activeLoan.markReturned();
        }

        return member.getName() + " が " + book.getTitle() + " を返却しました";
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Loan> getActiveLoans() {
        List<Loan> activeLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.isActive()) {
                activeLoans.add(loan);
            }
        }
        return activeLoans;
    }

    private Book findBook(String bookId) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    private Member findMember(String memberId) {
        for (Member member : members) {
            if (member.getId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    private Loan findActiveLoan(String bookId, String memberId) {
        for (Loan loan : loans) {
            if (loan.isActive() && loan.getBookId().equals(bookId) && loan.getMemberId().equals(memberId)) {
                return loan;
            }
        }
        return null;
    }
}
