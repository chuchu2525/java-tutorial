package com.example.demo.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.library.dto.ActionResponse;
import com.example.demo.library.repository.LibraryRepository;
import org.junit.jupiter.api.Test;

class LibraryServiceTests {

    @Test
    void borrowAndReturnFlowWorks() {
        LibraryService libraryService = new LibraryService(new LibraryRepository(), new LoanPolicy());

        ActionResponse firstLoan = libraryService.borrowBook("m1", "b1");
        ActionResponse duplicateLoan = libraryService.borrowBook("m2", "b1");
        ActionResponse secondLoan = libraryService.borrowBook("m1", "b2");
        ActionResponse overLimitLoan = libraryService.borrowBook("m1", "b3");
        ActionResponse returnResponse = libraryService.returnBook("m1", "b1");
        ActionResponse loanAfterReturn = libraryService.borrowBook("m1", "b3");

        assertTrue(firstLoan.isSuccess());
        assertFalse(duplicateLoan.isSuccess());
        assertTrue(secondLoan.isSuccess());
        assertFalse(overLimitLoan.isSuccess());
        assertTrue(returnResponse.isSuccess());
        assertTrue(loanAfterReturn.isSuccess());
        assertEquals(2, libraryService.getActiveLoans().size());
    }

    @Test
    void addBookAndMemberAndReturnByBookIdWork() {
        LibraryService libraryService = new LibraryService(new LibraryRepository(), new LoanPolicy());

        ActionResponse addBookResponse = libraryService.addBook("DDD入門", "Kato");
        ActionResponse addMemberResponse = libraryService.addMember("Haru");
        ActionResponse borrowResponse = libraryService.borrowBook("m3", "b4");
        ActionResponse returnResponse = libraryService.returnBookByBookId("b4");

        assertTrue(addBookResponse.isSuccess());
        assertTrue(addMemberResponse.isSuccess());
        assertTrue(borrowResponse.isSuccess());
        assertTrue(returnResponse.isSuccess());
        assertEquals(0, libraryService.getActiveLoans().size());
        assertEquals(4, libraryService.getBooks().size());
        assertEquals(3, libraryService.getMembers().size());
    }
}
