package com.example.demo.library.view;

import com.example.demo.library.model.Book;

/**
 * 画面の本一覧・貸出中用の行データ。
 */
public record BookRowView(String id, String title, String author, String statusLabel, boolean borrowed) {

    public static BookRowView from(Book book) {
        return new BookRowView(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getStatusLabel(),
                book.isBorrowed());
    }
}
