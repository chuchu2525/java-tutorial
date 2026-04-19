package com.example.demo.library.web;

import com.example.demo.library.controller.LibraryPageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice(assignableTypes = LibraryPageController.class)
public class LibraryPageExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(LibraryPageExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String handleUnexpected(Exception ex, RedirectAttributes redirectAttributes) {
        log.error("Unexpected error on library page", ex);
        redirectAttributes.addFlashAttribute("message", "予期しないエラーが発生しました。しばらくしてから再度お試しください。");
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/library";
    }
}
