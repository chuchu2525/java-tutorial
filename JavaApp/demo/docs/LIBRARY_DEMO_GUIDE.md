# 図書貸出デモの見どころ

このデモは、同じ題材を `JavaSample` のコンソール版と `JavaApp/demo` の Spring Boot 版で比較できるようにしています。

## 1. OOP と Spring の対応

- コンソール版の `Book`、`Member`、`Loan` は、そのまま Spring 版の `model` に対応します
- コンソール版の `LoanPolicy` は、Spring 版では `@Component` として DI されます
- コンソール版の `LibraryService` は、Spring 版でも業務ルールの中心です
- コンソール版では `main` が `new` で依存関係を組み立てます
- Spring 版では `LibraryController` と `LibraryService` がコンストラクタインジェクションでつながります

## 2. 先に読むとよいファイル

以下の `src/` で始まるパスは、このドキュメントのひとつ上の **`demo` ディレクトリ** をルートとした相対パスです。

- `JavaSample/lesson21_oop_library_demo/Lesson21OopLibraryDemo.java`
- `src/main/java/com/example/demo/library/controller/LibraryPageController.java`
- `src/main/resources/templates/library.html`
- `src/main/java/com/example/demo/library/controller/LibraryController.java`
- `src/main/java/com/example/demo/library/service/LibraryService.java`
- `src/main/java/com/example/demo/library/repository/LibraryRepository.java`
- `src/main/java/com/example/demo/library/service/LoanPolicy.java`

## 3. Spring Boot 版の役割分担

- `Controller`: HTTP リクエストを受け取り、画面やレスポンスに変換する
- `Service`: 貸出・返却の業務ルールを実行する
- `Repository`: 本、会員、貸出データをメモリ上で保持する
- `Model`: 状態と振る舞いを持つドメインオブジェクト

## 4. View

ブラウザで次の URL を開くと、Spring MVC の画面から本一覧、会員一覧、貸出中一覧、貸出、返却、本追加、会員登録を試せます。

```text
http://localhost:8080/library
```

この画面は `LibraryPageController` がテンプレート `templates/library.html` を返す形になっています。
そのため、`Controller -> Service -> Repository -> View` の MVC の流れを読み取りやすい教材になっています。

### 画面で確認できること

- 本一覧は `ID / タイトル / 著者 / ステータス` で表示される
- ステータスは `貸出可能` と `貸出中` で表示される
- 貸出フォームは `会員` と `貸出可能な本` のプルダウンから選べる
- 返却フォームは `貸出中の本` だけをプルダウン表示する
- 画面から本追加と会員登録もできる

## 5. 試しやすい API

### 本一覧

```http
GET /api/library/books
```

### 会員一覧

```http
GET /api/library/members
```

### 貸出中一覧

```http
GET /api/library/loans
```

### 貸出

```http
POST /api/library/loans
Content-Type: application/json

{
  "memberId": "m1",
  "bookId": "b1"
}
```

### 返却

```http
POST /api/library/returns
Content-Type: application/json

{
  "memberId": "m1",
  "bookId": "b1"
}
```

## 6. 学習ポイント

- OOP では「どのクラスが何を知るべきか」を見る
- Spring では「どのオブジェクトを誰が作って注入するか」を見る
- MVC では「Controller が Model を View に渡す流れ」を見る
- フォームでは「選べる値をサーバー側がどう用意するか」を見る
- まずコンソール版を読んでから Spring 版へ移ると、フレームワークが隠してくれる処理を理解しやすいです
