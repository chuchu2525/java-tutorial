# Lesson 21: OOP Library Demo

この lesson では、図書貸出アプリを題材にして、複数クラスが協力して動く OOP の考え方を学びます。

## この lesson で見るポイント

- `Book`: 本の状態を持つ
- `Member`: 会員が今何冊借りているかを持つ
- `Loan`: 貸出という出来事を表す
- `LoanPolicy`: 借りられるかどうかのルールを判定する
- `LibraryService`: 貸出と返却の流れをまとめる

## 読む順番

1. `Lesson21OopLibraryDemo` の `main`
2. `LibraryService`
3. `LoanPolicy`
4. `Book` と `Member`
5. `Loan`

## 学習の意図

このサンプルでは、`main` が `new` を使ってオブジェクトを組み立てています。
ここが Spring Boot 版との大きな比較ポイントです。

- コンソール版: 自分でオブジェクトを作って渡す
- Spring Boot 版: Spring がオブジェクトを作って注入する

## 動作の流れ

1. 本と会員を登録する
2. 会員が本を借りる
3. すでに貸出中の本は借りられない
4. 上限冊数を超えると借りられない
5. 返却すると別の本を借りられる

## 次に見るとよい場所

OOP の流れが読めたら、次は Spring Boot 側の次のファイルを見ると比較しやすいです。

- `JavaApp/demo/src/main/java/com/example/demo/library/controller/LibraryController.java`
- `JavaApp/demo/src/main/java/com/example/demo/library/service/LibraryService.java`
- `JavaApp/demo/src/main/java/com/example/demo/library/repository/LibraryRepository.java`
