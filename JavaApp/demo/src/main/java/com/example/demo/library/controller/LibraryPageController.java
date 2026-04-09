package com.example.demo.library.controller;

import com.example.demo.library.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/library")
public class LibraryPageController {
    private final LibraryService libraryService;

    public LibraryPageController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public String showLibraryPage(Model model) {
        model.addAttribute("books", libraryService.getBooks());
        model.addAttribute("members", libraryService.getMembers());
        model.addAttribute("availableBooks", libraryService.getAvailableBooks());
        model.addAttribute("borrowedBooks", libraryService.getBorrowedBooks());
        return "library";
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam String memberId,
                             @RequestParam String bookId,
                             RedirectAttributes redirectAttributes) {
        addMessage(redirectAttributes, libraryService.borrowBook(memberId, bookId));
        return "redirect:/library";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam String bookId,
                             RedirectAttributes redirectAttributes) {
        addMessage(redirectAttributes, libraryService.returnBookByBookId(bookId));
        return "redirect:/library";
    }

    @PostMapping("/books")
    public String addBook(@RequestParam String title,
                          @RequestParam String author,
                          RedirectAttributes redirectAttributes) {
        addMessage(redirectAttributes, libraryService.addBook(title, author));
        return "redirect:/library";
    }

    @PostMapping("/members")
    public String addMember(@RequestParam String name,
                            RedirectAttributes redirectAttributes) {
        addMessage(redirectAttributes, libraryService.addMember(name));
        return "redirect:/library";
    }

    private void addMessage(RedirectAttributes redirectAttributes,
                            com.example.demo.library.dto.ActionResponse response) {
        redirectAttributes.addFlashAttribute("message", response.getMessage());
        redirectAttributes.addFlashAttribute("messageType", response.isSuccess() ? "success" : "error");
    }
}
