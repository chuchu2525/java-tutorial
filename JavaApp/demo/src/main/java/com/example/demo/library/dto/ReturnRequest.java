package com.example.demo.library.dto;

import jakarta.validation.constraints.NotBlank;

public class ReturnRequest {
    @NotBlank(message = "会員IDを入力してください")
    private String memberId;

    @NotBlank(message = "本のIDを入力してください")
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
