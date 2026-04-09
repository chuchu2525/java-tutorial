package com.example.demo.library.dto;

import jakarta.validation.constraints.NotBlank;

public class ReturnForm {
    @NotBlank(message = "本を選択してください")
    private String bookId;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
