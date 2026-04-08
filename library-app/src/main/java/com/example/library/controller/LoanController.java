package com.example.library.controller;

import com.example.library.domain.Loan;
import com.example.library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LibraryService libraryService;

    @PostMapping("/borrow")
    public Loan borrowBook(@RequestParam Long memberId, @RequestParam Long bookId) {
        return libraryService.borrowBook(memberId, bookId);
    }

    @PostMapping("/{loanId}/return")
    public void returnBook(@PathVariable Long loanId) {
        libraryService.returnBook(loanId);
    }
}
