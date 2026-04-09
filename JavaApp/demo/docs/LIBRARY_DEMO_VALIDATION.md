# 図書貸出デモ：入力バリデーション

このドキュメントは、`JavaApp/demo` の図書貸出デモに **Bean Validation（Jakarta Validation）** を入れた変更の要約です。[LIBRARY_DEMO_ROADMAP.md](LIBRARY_DEMO_ROADMAP.md) の「バリデーションを入れる」に対応します。

以下の `src/` で始まるパスは、このファイルと同じ階層のひとつ上の **`demo` ディレクトリ** をルートとした相対パスです。

## 何をしたか

### 依存関係

- `spring-boot-starter-validation` を追加し、Hibernate Validator によるアノテーションベースの検証を有効にした。

### MVC 画面（`/library`）

- フォームごとに **専用のフォームクラス** を用意し、`@NotBlank` や `@Size` でサーバー側検証を行う。
  - `BorrowForm`（会員 ID・本 ID）
  - `ReturnForm`（本 ID）
  - `AddBookForm`（タイトル・著者）
  - `AddMemberForm`（名前）
- `LibraryPageController` は `@Valid` と `BindingResult` を使う。
  - **検証エラー時**: 一覧データを載せ直してテンプレート `library` をそのまま返す（リダイレクトしないため、フィールドエラーを表示できる）。
  - **成功時**: 従来どおり `RedirectAttributes` でメッセージを載せて `redirect:/library`。
- 共通で一覧用データと空のフォームを載せる処理を `populateLibraryPage` にまとめた。検証失敗時は、送信されたフォームだけ `BindingResult` 付きで残り、他フォームは未送信なら空インスタンスを補う。
- `templates/library.html` では各フォームに `th:object` / `th:field` / `th:errors` を使い、ブラウザの `required` に頼らずサーバー検証を主とする。

### REST API（`/api/library`）

- `LoanRequest` / `ReturnRequest` の `memberId` と `bookId` に `@NotBlank` を付与。
- `LibraryController` の POST で `@Valid` を指定。
- `LibraryRestExceptionHandler`（`@RestControllerAdvice`、`assignableTypes = LibraryController.class`）で `MethodArgumentNotValidException` を捕捉し、HTTP 400 と次の形の JSON を返す。
  - `message`: 固定の概要文
  - `errors`: `field` と `message` の配列

### 業務ロジック層との関係

- `LibraryService` の `addBook` / `addMember` にある空文字チェックは **そのまま残している**。コントローラ通過後も、別経路からサービスが呼ばれる可能性に備えた二重化として位置づけられる。

### 自動テスト

- `LibraryControllerValidationTests`: `POST /api/library/loans` に空の JSON `{}` を送り、400 と `message` / `errors` が返ることを `MockMvc` で検証。
- Spring Boot 4 ではスライス用の `@WebMvcTest` のパッケージが変わっているため、このテストは **`@SpringBootTest` と `@AutoConfigureMockMvc`**（`org.springframework.boot.webmvc.test.autoconfigure`）でフルコンテキストを起動している。

## 主なファイル一覧

| 種別 | パス |
|------|------|
| 依存 | `pom.xml` |
| MVC コントローラ | `src/main/java/.../library/controller/LibraryPageController.java` |
| REST コントローラ | `src/main/java/.../library/controller/LibraryController.java` |
| REST 例外ハンドラ | `src/main/java/.../library/controller/LibraryRestExceptionHandler.java` |
| フォーム | `src/main/java/.../library/dto/BorrowForm.java` ほか 3 クラス |
| API 用 DTO | `src/main/java/.../library/dto/LoanRequest.java`, `ReturnRequest.java` |
| テンプレート | `src/main/resources/templates/library.html` |
| テスト | `src/test/java/.../library/controller/LibraryControllerValidationTests.java` |

## 手元での確認のしかた（簡易）

- ブラウザで `http://localhost:8080/library` を開き、各フォームで未選択・未入力のまま送信し、赤い `.field-error` のメッセージが出ること。
- 開発者ツールで `required` を削除してから送信しても、同様にサーバー側で弾けること（任意だが効果的）。
- `curl` 等で `POST /api/library/loans` に `{}` を送り、400 と JSON の `errors` が返ること。

## コミットメッセージの例

履歴を読んだときに「何をしたか」が一発で分かるようにする例です。プロジェクトの運用に合わせて接頭辞（例: `feat(demo):`）を付けてもよいです。

```text
feat(demo): 図書デモに Bean Validation を導入する

- spring-boot-starter-validation を追加
- MVC はフォーム DTO、@Valid、BindingResult、th:errors でサーバー側検証
- REST の Loan/Return リクエストに @NotBlank と @Valid、400 用 RestControllerAdvice
- API バリデーションの MockMvc テストを追加
- 変更内容を docs/LIBRARY_DEMO_VALIDATION.md に記載
```

1 行だけにまとめる場合の例:

```text
feat(demo): 図書貸出デモに MVC/REST の入力バリデーションとドキュメントを追加
```
