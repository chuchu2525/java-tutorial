# LoanPolicy と責務分担

このメモは、図書貸出デモの DB 化で `LoanPolicy` をどう扱うかを整理したものです。

## 一言でいうと

`LoanPolicy` は DB を見に行かない。

`LoanPolicy` は、`LibraryService` から渡された材料だけを見て「貸出可能かどうか」を判定する部品にする。

## 役割分担

図書貸出の処理では、次のように責務を分ける。

| クラス | 役割 |
| --- | --- |
| `LibraryService` | 貸出処理全体の流れを組み立てる |
| `LibraryRepository` | DB やメモリからデータを取得・保存する |
| `LoanPolicy` | 渡された材料から貸出可否だけを判定する |

`LibraryService` は Repository ではない。
DB に関係するデータを必要とするが、DB への実アクセスは `LibraryRepository` に依頼する。

```text
Controller
  -> LibraryService
       -> LibraryRepository から材料を集める
       -> LoanPolicy に判定させる
       -> OK なら Loan を作って保存する
```

## LibraryService が集める材料

DB 化後の貸出処理では、`LibraryService` が次の材料を集める。

- `member`: 会員 ID に対応する会員
- `book`: 本 ID に対応する本
- `unreturnedLoan`: 対象の本に紐づく未返却 loan
- `unreturnedLoanCount`: 対象会員の未返却 loan 件数

イメージは次のようになる。

```java
Member member = libraryRepository.findMemberById(memberId);
Book book = libraryRepository.findBookById(bookId);
Loan unreturnedLoan = libraryRepository.findUnreturnedLoanByBookId(bookId);
long unreturnedLoanCount = libraryRepository.countUnreturnedLoansByMemberId(memberId);

String result = loanPolicy.validateBorrow(
        member,
        book,
        unreturnedLoan,
        unreturnedLoanCount
);
```

## LoanPolicy が判定すること

`LoanPolicy` は、渡された材料だけで次を判定する。

- `member == null` なら NG
- `book == null` なら NG
- `unreturnedLoan != null` なら NG
- `unreturnedLoanCount >= 上限` なら NG
- それ以外は OK

イメージは次のようになる。

```java
if (member == null) {
    return "会員が見つかりません";
}
if (book == null) {
    return "本が見つかりません";
}
if (unreturnedLoan != null) {
    return "その本はすでに貸出中です";
}
if (unreturnedLoanCount >= MAX_BOOKS_PER_MEMBER) {
    return "貸出上限に達しているため借りられません";
}
return "OK";
```

## LoanPolicy に Repository を注入しない理由

`LoanPolicy` に `LibraryRepository` や JPA repository を注入すると、`LoanPolicy` が次の 2 つの責務を持ってしまう。

- DB から情報を探す
- 貸出ルールを判定する

これは責務が重い。

`LoanPolicy` は本来、貸出ルールを表す部品なので、DB の存在を知らなくてよい。
DB を H2 から別の DB に変えても、`LoanPolicy` の判定ロジックは変わらないのが望ましい。

## テストしやすさ

`LoanPolicy` が DB を見ない形なら、DB や mock repository を用意しなくてもテストできる。

```java
String result = loanPolicy.validateBorrow(member, book, null, 1);
```

このように、材料を渡すだけで判定を確認できる。

一方で `LoanPolicy` が Repository を持つと、単純なルール判定のテストにも DB や mock が必要になり、学習用コードとしても読みづらくなる。

## まとめ

今回の設計意図は、責務とレイヤーを混ぜないこと。

- DB への実アクセスは `LibraryRepository`
- 貸出処理の流れは `LibraryService`
- 貸出可否の判定は `LoanPolicy`

この分担を守るため、`LoanPolicy` は Repository を注入せず、渡された材料だけで判定する。
