package com.example.demo.library.controller;

import com.example.demo.library.dto.ActionResponse;
import com.example.demo.library.dto.LoanRequest;
import com.example.demo.library.dto.ReturnRequest;
import com.example.demo.library.model.Book;
import com.example.demo.library.model.Loan;
import com.example.demo.library.model.Member;
import com.example.demo.library.service.LibraryService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/library")
public class LibraryController {
    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return libraryService.getBooks();
    }

    @GetMapping("/members")
    public List<Member> getMembers() {
        return libraryService.getMembers();
    }

    @GetMapping("/loans")
    public List<Loan> getActiveLoans() {
        return libraryService.getActiveLoans();
    }

    @PostMapping("/loans")
    public ResponseEntity<ActionResponse> borrowBook(@Valid @RequestBody LoanRequest request) {
        ActionResponse response = libraryService.borrowBook(request.getMemberId(), request.getBookId());
        return buildResponse(response);
    }

    @PostMapping("/returns")
    public ResponseEntity<ActionResponse> returnBook(@Valid @RequestBody ReturnRequest request) {
        ActionResponse response = libraryService.returnBook(request.getMemberId(), request.getBookId());
        return buildResponse(response);
    }

    private ResponseEntity<ActionResponse> buildResponse(ActionResponse response) {
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }
}
