# 図書貸出デモ DB 化 実装計画

## 目的

現在の図書貸出デモは、`LibraryRepository` が `Map` と `List` でデータをメモリ上に保持している。
この計画では、既存の画面/API/業務ルールを保ったまま、保存先を H2 + Spring Data JPA に置き換える。

DB 化によって、次の学習テーマを扱える状態にする。

- Spring Data JPA の基本
- Entity と Repository の役割
- 起動時の初期データ投入
- in-memory 実装から DB 実装への置き換え
- DB を使ったテスト

## 現在の状態

- `/library` の MVC 画面がある。
- `/api/library/*` の REST API がある。
- 貸出、返却、本追加、会員登録ができる。
- Bean Validation と例外ハンドラが導入済み。
- 画面用 ViewModel が分離済み。
- `LibraryRepository` は in-memory 実装。
- `Book` / `Member` / `Loan` は JPA Entity ではない通常の Java クラス。

## 実装方針

教材としての読みやすさを優先し、初回の DB 化では構成を大きく複雑にしない。

- `Book` / `Member` / `Loan` を JPA Entity 化する。
- `LibraryRepository` は facade として残し、内部で Spring Data JPA repository interface を使う。
- Controller、Service、DTO、ViewModel、Thymeleaf テンプレートの外部仕様は維持する。
- ID は既存どおり `b1`, `b2`, `m1`, `m2` 形式を維持する。
- 初期データは DB が空のときだけ投入する。
- H2 は開発・学習用途として使い、本番 DB や migration tool は導入しない。

## 変更対象

### 依存関係

`pom.xml` に次を追加する。

- `spring-boot-starter-data-jpa`
- `h2`

### 設定

`application.properties` に H2 と JPA の設定を追加する。

- H2 datasource
- JPA ddl-auto
- SQL 表示は学習用に必要なら有効化
- H2 console

### モデル

`Book` は次の形にする。

- `@Entity`
- `@Id String id`
- `title`
- `author`
- `borrowed`
- `borrowedByMemberId`
- JPA 用 no-args constructor
- 既存の `borrow` / `giveBack` / `isAvailable` などの振る舞いは維持

`Member` は次の形にする。

- `@Entity`
- `@Id String id`
- `name`
- `@ElementCollection List<String> borrowedBookIds`
- JPA 用 no-args constructor
- 既存の `borrowBook` / `returnBook` / `hasBook` などの振る舞いは維持

`Loan` は次の形にする。

- `@Entity`
- DB 用主キー `@Id @GeneratedValue Long id`
- `bookId`
- `memberId`
- `loanDate`
- `active`
- JPA 用 no-args constructor
- 既存の `markReturned` は維持

### Repository

Spring Data JPA repository interface を追加する。

- `BookJpaRepository`
- `MemberJpaRepository`
- `LoanJpaRepository`

`LibraryRepository` は既存 Service からの呼び出し口として残し、次の責務にする。

- 全件取得
- ID 検索
- 本追加
- 会員追加
- 貸出履歴保存
- active loan 検索
- 変更済み Entity の保存

### 初期データ

起動時に DB が空なら初期データを投入する。

- `b1`: `Java入門` / `Yamada`
- `b2`: `Spring Boot入門` / `Suzuki`
- `b3`: `設計の基本` / `Tanaka`
- `m1`: `Yuu`
- `m2`: `Aoi`

DB が空でない場合は投入しない。

## 実装手順

1. `pom.xml` に JPA と H2 の依存関係を追加する。
2. `application.properties` に H2/JPA 設定を追加する。
3. `Book` / `Member` / `Loan` を Entity 化する。
4. Spring Data JPA repository interface を追加する。
5. `LibraryRepository` を JPA repository 利用に置き換える。
6. `LibraryService` で状態変更後に保存が必要な Entity を保存する。
7. 初期データ投入クラスを追加する。
8. 既存テストを DB 前提に更新する。
9. `./mvnw test` で全体を確認する。
10. 必要なら `/library` 画面で手動確認する。

## テスト計画

### Service

DB 経由で次を確認する。

- 本を借りられる。
- すでに貸出中の本は借りられない。
- 1 人が借りられる冊数の上限が守られる。
- 返却できる。
- 本 ID だけで返却できる。
- 本を追加できる。
- 会員を追加できる。
- active loan の件数が正しい。

### MVC/API

既存のテストを維持し、DB 化後も通ることを確認する。

- API の Bean Validation エラーが 400 で返る。
- 不正 JSON が統一された 400 レスポンスになる。
- MVC の想定外例外がフラッシュメッセージ付きで `/library` に戻る。
- Spring Boot の context が起動する。

### 実行コマンド

```bash
./mvnw test
```

## 今回の対象外

- 本番 DB 対応
- Flyway / Liquibase の導入
- 認証・認可
- 貸出履歴の詳細画面
- `Book` と `Member` を外部キーで完全に関連付ける正規化
- REST API のレスポンス形式変更
- 画面デザインの大幅変更

## 完了条件

- in-memory の `Map` / `List` 保持が DB 保存に置き換わっている。
- 起動時に初期データが投入される。
- `/library` で既存と同じ操作ができる。
- `/api/library/*` の既存 API が動く。
- `./mvnw test` が成功する。
- 既存の学習用コードとして追いやすい構成を維持している。
