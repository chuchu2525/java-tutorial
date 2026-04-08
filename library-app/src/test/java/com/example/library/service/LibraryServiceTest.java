package com.example.library.service;

import com.example.library.domain.*;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LoanRepository;
import com.example.library.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LibraryService libraryService;

    private Book book;
    private RegularMember regularMember;
    private PremiumMember premiumMember;

    @BeforeEach
    void setUp() {
        book = new Book("Clean Code", "Robert C. Martin");
        book.setId(1L);
        regularMember = new RegularMember("Alice");
        regularMember.setId(1L);
        premiumMember = new PremiumMember("Bob");
        premiumMember.setId(2L);
    }

    @Test
    void borrowBook_ShouldSucceed_WhenRegularMemberWithinLimit() {
        when(memberRepository.findById(1L)).thenReturn(Optional.of(regularMember));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(loanRepository.findByMemberAndReturnDateIsNull(regularMember)).thenReturn(new ArrayList<>());
        when(loanRepository.save(any(Loan.class))).thenAnswer(i -> i.getArguments()[0]);

        Loan loan = libraryService.borrowBook(1L, 1L);

        assertNotNull(loan);
        assertFalse(book.isAvailable());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void borrowBook_ShouldFail_WhenRegularMemberExceedsLimit() {
        when(memberRepository.findById(1L)).thenReturn(Optional.of(regularMember));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        List<Loan> activeLoans = new ArrayList<>();
        activeLoans.add(new Loan());
        activeLoans.add(new Loan());
        activeLoans.add(new Loan()); // Limit is 3

        when(loanRepository.findByMemberAndReturnDateIsNull(regularMember)).thenReturn(activeLoans);

        assertThrows(IllegalStateException.class, () -> libraryService.borrowBook(1L, 1L));
    }

    @Test
    void borrowBook_ShouldSucceed_WhenPremiumMemberExceedsRegularLimit() {
        when(memberRepository.findById(2L)).thenReturn(Optional.of(premiumMember));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        List<Loan> activeLoans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            activeLoans.add(new Loan());
        }

        when(loanRepository.findByMemberAndReturnDateIsNull(premiumMember)).thenReturn(activeLoans);
        when(loanRepository.save(any(Loan.class))).thenAnswer(i -> i.getArguments()[0]);

        Loan loan = libraryService.borrowBook(2L, 1L);

        assertNotNull(loan);
        verify(loanRepository, times(1)).save(any(Loan.class));
    }
}
