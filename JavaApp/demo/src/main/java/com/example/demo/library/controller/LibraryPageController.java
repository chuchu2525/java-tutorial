package com.example.demo.library.controller;

import com.example.demo.library.dto.ActionResponse;
import com.example.demo.library.dto.AddBookForm;
import com.example.demo.library.dto.AddMemberForm;
import com.example.demo.library.dto.BorrowForm;
import com.example.demo.library.dto.ReturnForm;
import com.example.demo.library.service.LibraryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        populateLibraryPage(model);
        return "library";
    }

    @PostMapping("/borrow")
    public String borrowBook(@Valid @ModelAttribute("borrowForm") BorrowForm borrowForm,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            populateLibraryPage(model);
            return "library";
        }
        addMessage(redirectAttributes, libraryService.borrowBook(borrowForm.getMemberId(), borrowForm.getBookId()));
        return "redirect:/library";
    }

    @PostMapping("/return")
    public String returnBook(@Valid @ModelAttribute("returnForm") ReturnForm returnForm,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            populateLibraryPage(model);
            return "library";
        }
        addMessage(redirectAttributes, libraryService.returnBookByBookId(returnForm.getBookId()));
        return "redirect:/library";
    }

    @PostMapping("/books")
    public String addBook(@Valid @ModelAttribute("addBookForm") AddBookForm addBookForm,
                          BindingResult bindingResult,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            populateLibraryPage(model);
            return "library";
        }
        addMessage(redirectAttributes, libraryService.addBook(addBookForm.getTitle(), addBookForm.getAuthor()));
        return "redirect:/library";
    }

    @PostMapping("/members")
    public String addMember(@Valid @ModelAttribute("addMemberForm") AddMemberForm addMemberForm,
                            BindingResult bindingResult,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            populateLibraryPage(model);
            return "library";
        }
        addMessage(redirectAttributes, libraryService.addMember(addMemberForm.getName()));
        return "redirect:/library";
    }

    private void populateLibraryPage(Model model) {
        model.addAttribute("books", libraryService.getBooks());
        model.addAttribute("members", libraryService.getMembers());
        model.addAttribute("availableBooks", libraryService.getAvailableBooks());
        model.addAttribute("borrowedBooks", libraryService.getBorrowedBooks());
        if (!model.containsAttribute("borrowForm")) {
            model.addAttribute("borrowForm", new BorrowForm());
        }
        if (!model.containsAttribute("returnForm")) {
            model.addAttribute("returnForm", new ReturnForm());
        }
        if (!model.containsAttribute("addBookForm")) {
            model.addAttribute("addBookForm", new AddBookForm());
        }
        if (!model.containsAttribute("addMemberForm")) {
            model.addAttribute("addMemberForm", new AddMemberForm());
        }
    }

    private void addMessage(RedirectAttributes redirectAttributes, ActionResponse response) {
        redirectAttributes.addFlashAttribute("message", response.getMessage());
        redirectAttributes.addFlashAttribute("messageType", response.isSuccess() ? "success" : "error");
    }
}
