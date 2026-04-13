package com.example.demo.library.controller;

import com.example.demo.library.dto.ActionResponse;
import com.example.demo.library.dto.AddBookForm;
import com.example.demo.library.dto.AddMemberForm;
import com.example.demo.library.dto.BorrowForm;
import com.example.demo.library.dto.ReturnForm;
import com.example.demo.library.model.Book;
import com.example.demo.library.service.LibraryService;
import com.example.demo.library.view.BookRowView;
import com.example.demo.library.view.LibraryPageViewModel;
import com.example.demo.library.view.MemberRowView;
import com.example.demo.library.view.SelectOptionView;
import jakarta.validation.Valid;
import java.util.List;
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
        populateViewData(model);
        populateForms(model);
    }

    private void populateViewData(Model model) {
        model.addAttribute("page", buildPageViewModel());
    }

    private void populateForms(Model model) {
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

    private LibraryPageViewModel buildPageViewModel() {
        List<BookRowView> books = libraryService.getBooks().stream().map(BookRowView::from).toList();
        List<MemberRowView> members = libraryService.getMembers().stream().map(MemberRowView::from).toList();

        List<Book> availableDomain = libraryService.getAvailableBooks();
        List<SelectOptionView> availableBookOptions = availableDomain.stream()
                .map(b -> new SelectOptionView(b.getId(), b.getId() + " / " + b.getTitle()))
                .toList();

        List<Book> borrowedDomain = libraryService.getBorrowedBooks();
        List<BookRowView> borrowedBooks = borrowedDomain.stream().map(BookRowView::from).toList();
        List<SelectOptionView> borrowedBookOptions = borrowedDomain.stream()
                .map(b -> new SelectOptionView(b.getId(), b.getId() + " / " + b.getTitle()))
                .toList();

        return new LibraryPageViewModel(books, members, availableBookOptions, borrowedBooks, borrowedBookOptions);
    }

    private void addMessage(RedirectAttributes redirectAttributes, ActionResponse response) {
        redirectAttributes.addFlashAttribute("message", response.getMessage());
        redirectAttributes.addFlashAttribute("messageType", response.isSuccess() ? "success" : "error");
    }
}
