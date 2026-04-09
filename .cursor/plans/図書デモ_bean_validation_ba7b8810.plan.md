---
name: 図書デモ Bean Validation
overview: "`spring-boot-starter-validation` を追加し、MVC はフォーム用オブジェクト＋`BindingResult` でフィールドエラーを表示、REST は `@Valid` と（必要なら）400 用の例外ハンドラで入力不正を返す。"
todos:
  - id: add-validation-dep
    content: pom.xml に spring-boot-starter-validation を追加
    status: completed
  - id: mvc-forms-controller
    content: 4 つの Form クラス作成、LibraryPageController を @Valid + BindingResult + populate 抽出に変更
    status: completed
  - id: thymeleaf-errors
    content: library.html に th:object / th:field / th:errors を反映
    status: completed
  - id: rest-dto-valid
    content: LoanRequest・ReturnRequest に @NotBlank、LibraryController に @Valid
    status: completed
  - id: rest-advice
    content: MethodArgumentNotValidException 用 @RestControllerAdvice を追加
    status: completed
  - id: test-mockmvc
    content: API バリデーションの MockMvc テストを 1 件追加（推奨）
    status: completed
isProject: false
---

# 図書貸出デモ：バリデーション導入プラン

## 現状

- [LibraryPageController.java](JavaApp/demo/src/main/java/com/example/demo/library/controller/LibraryPageController.java): 4 つの POST がすべて `@RequestParam`。HTML は `required` のみ（ブラウザ依存・空白のみは抜けうる）。
- [LibraryController.java](JavaApp/demo/src/main/java/com/example/demo/library/controller/LibraryController.java): `LoanRequest` / `ReturnRequest` を JSON で受け取り、Bean Validation なし。
- [LibraryService.java](JavaApp/demo/src/main/java/com/example/demo/library/service/LibraryService.java): `addBook` / `addMember` で trim 後の空文字は既に拒否。貸出は [LoanPolicy](JavaApp/demo/src/main/java/com/example/demo/library/service/LoanPolicy.java) で null 会員・本をメッセージ化。
- [pom.xml](JavaApp/demo/pom.xml): `spring-boot-starter-validation` 未追加。

## 方針

1. **依存関係**: `spring-boot-starter-validation` を [pom.xml](JavaApp/demo/pom.xml) に追加（Jakarta Bean Validation + Hibernate Validator）。
2. **MVC**: ロードマップどおり **`@ModelAttribute` + `@Valid` + `BindingResult`** でサーバー側検証し、[library.html](JavaApp/demo/src/main/resources/templates/library.html) で **`th:errors`** を表示する。
3. **REST**: 既存 DTO に **`@NotBlank`**（必要なら `@Size`）を付け、コントローラで **`@Valid`**。400 時の JSON を教材として分かりやすくするため、**`@RestControllerAdvice` で `MethodArgumentNotValidException` をハンドル**し、フィールド名とメッセージのリスト（または単純な `message` 文字列）を返す。
4. **Service の空チェック**: 二重になっても学習上問題なし。**そのまま残す**（他呼び出し経路・将来の API 拡張の安全策）。メッセージ文言が MVC の `BindingResult` と二系統になる点は、必要なら後続で揃えればよい。

## 実装タスク

### 1. 依存関係

- [pom.xml](JavaApp/demo/pom.xml) に `org.springframework.boot:spring-boot-starter-validation` を追加。

### 2. フォーム用クラス（MVC）

`com.example.demo.library.dto` に 4 クラスを追加（名前は例）:

| クラス          | フィールド           | 制約の目安                           |
| --------------- | -------------------- | ------------------------------------ |
| `BorrowForm`    | `memberId`, `bookId` | ともに `@NotBlank`                   |
| `ReturnForm`    | `bookId`             | `@NotBlank`                          |
| `AddBookForm`   | `title`, `author`    | `@NotBlank`、任意で `@Size(max = …)` |
| `AddMemberForm` | `name`               | `@NotBlank`、任意で `@Size`          |

フォームの `name` 属性とプロパティ名を一致させ、既存 HTML のフィールド名をそのまま活かす。

### 3. LibraryPageController

- `GET /library`: 一覧用データに加え、各フォーム用に **空のインスタンス** を `Model` に載せる（例: `borrowForm`, `returnForm`, `addBookForm`, `addMemberForm`）。これで Thymeleaf の `th:object` が常に安全。
- 共通処理を **private メソッド** に抽出（例: `populateLibraryPage(Model)`）し、GET と **検証失敗時の POST** の両方から呼ぶ。
- 各 POST:
  - 引数を `@Valid @ModelAttribute(...) XxxForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes` に変更。
  - `bindingResult.hasErrors()` のとき: `populateLibraryPage(model)` のみ（失敗したフォームは Spring が Model に残す）、**`return "library"`**（リダイレクトしない — エラー表示のため）。
  - 成功時のみ現状どおり `libraryService` 呼び出し → flash → **`redirect:/library`**。

```mermaid
flowchart LR
  GET[GET_library] --> populate[populateLibraryPage]
  POST[POST_forms] --> valid{@Valid}
  valid -->|ok| service[LibraryService]
  valid -->|errors| populate
  service --> redirect[redirect_flash]
  populate --> view[library.html]
```

### 4. library.html（Thymeleaf）

- 各 `<form>` に `th:object` / `th:action` を対応付け。
- テキスト入力は `th:field` を使うと `BindingResult` と連携しやすい。
- セレクトは `th:field="*{memberId}"` 等にし、先頭の空オプションは維持。**サーバー検証が主**なので、HTML の `required` は任意（残してもよいが、教材として「サーバーで必ず弾く」を見せるなら外す判断も可）。

### 5. REST DTO とコントローラ

- [LoanRequest.java](JavaApp/demo/src/main/java/com/example/demo/library/dto/LoanRequest.java) / [ReturnRequest.java](JavaApp/demo/src/main/java/com/example/demo/library/dto/ReturnRequest.java): `memberId`, `bookId` に `@NotBlank`。
- [LibraryController.java](JavaApp/demo/src/main/java/com/example/demo/library/controller/LibraryController.java): `borrowBook` / `returnBook` の引数に `@Valid` を付与。

### 6. 例外ハンドラ（REST 400）

- 新規クラス（例: `com.example.demo.library.controller.LibraryRestExceptionHandler` または `...web` パッケージ）に `@RestControllerAdvice`（basePackageClasses で library のみに限定すると他 API への影響を避けやすい）。
- `MethodArgumentNotValidException` を捕捉し、`ResponseEntity.badRequest()` で JSON を返す（例: `errors` マップ、または `field` / `defaultMessage` のリスト）。

### 7. テスト（最低限）

- **推奨**: `MockMvc` で `POST /api/library/loans` に `{}` や空文字を送り **400** と本文にバリデーション情報が含まれることを 1 ケース確認（新規 `@WebMvcTest(LibraryController.class)` または `@SpringBootTest` + `MockMvc`）。
- MVC の画面テストは任意（工数が増えるため、まずは REST の 1 本で「バリデーションが有効」を担保してもよい）。

## スコープ外（ロードマップの次フェーズ）

- View 専用 DTO の本格分離、例外処理の全面整理、DB 化は今回含めない。
