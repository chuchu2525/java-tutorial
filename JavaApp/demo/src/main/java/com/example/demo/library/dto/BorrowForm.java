package com.example.demo.library.dto;

import jakarta.validation.constraints.NotBlank;

public class BorrowForm {
    @NotBlank(message = "会員を選択してください")
    private String memberId;

    @NotBlank(message = "本を選択してください")
    private String bookId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
