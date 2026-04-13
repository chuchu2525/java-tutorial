package com.example.demo.library.view;

import com.example.demo.library.model.Member;

/**
 * 画面の会員一覧用の行データ。
 */
public record MemberRowView(String id, String name, int borrowedBookCount) {

    public static MemberRowView from(Member member) {
        return new MemberRowView(member.getId(), member.getName(), member.getBorrowedBookCount());
    }
}
