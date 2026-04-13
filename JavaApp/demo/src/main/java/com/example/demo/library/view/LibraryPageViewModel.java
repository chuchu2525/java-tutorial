package com.example.demo.library.view;

import java.util.List;

/**
 * 図書館画面の表示用データをまとめた ViewModel。
 */
public record LibraryPageViewModel(
        List<BookRowView> books,
        List<MemberRowView> members,
        List<SelectOptionView> availableBookOptions,
        List<BookRowView> borrowedBooks,
        List<SelectOptionView> borrowedBookOptions) {}
