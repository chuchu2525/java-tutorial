# 図書貸出デモの到達点と進める順序

このファイルは、図書貸出デモを学習教材としてどこまで育てるか、その順序を整理したメモです。

## いま完了しているところ

現在は次の状態までできています。

- `JavaSample` 側に OOP 学習用のコンソール版がある
- `JavaApp/demo` 側に Spring Boot の `Controller`、`Service`、`Repository`、`Model` がある
- `/api/library/*` の API がある
- `/library` の MVC 画面がある
- 画面から貸出、返却、本追加、会員登録ができる
- 本一覧で `貸出可能 / 貸出中` が分かる

この時点で、次の学習テーマまでは十分見えます。

- OOP の責務分割
- Spring の DI
- MVC の基本
- フォーム送信と画面再描画

## 最終的に目指すとよい範囲

学習用デモとしては、最終的には次のくらいまでを到達点にするとちょうど良いです。

### 1. OOP の理解

- ドメインモデルの責務が分かる
- `Service` が業務ルールをまとめる意味が分かる
- `Policy` のような判定クラスを分ける意味が分かる

### 2. Spring Boot の理解

- `Controller`、`Service`、`Repository` の分担が分かる
- コンストラクタインジェクションで依存がつながることが分かる
- DTO と画面用データの役割の違いが分かる

### 3. MVC の理解

- 一覧画面をサーバーサイドで描画できる
- フォーム送信から `Controller -> Service -> Repository -> View` の流れが追える
- 成功メッセージ、失敗メッセージを画面に返せる

### 4. 実務に近づける最小範囲

- 入力バリデーションがある
- 例外処理の置き場が整理されている
- In-memory の `Repository` を DB に置き換えられる
- テストが最低限そろっている

## おすすめの実装順

### Phase 1: Java の OOP を固める

- `Book`
- `Member`
- `Loan`
- `LoanPolicy`
- `LibraryService`
- `main`

この段階では、まず「どのクラスが何を担当するか」を理解するのが目的です。

### Phase 2: Spring Boot の API に移す

- `RestController`
- `Service`
- `Repository`
- メモリ保持のデータ

この段階では、OOP で作った処理を Spring のレイヤー構成に移すことが目的です。

### Phase 3: MVC 画面を付ける

- 一覧画面
- 貸出フォーム
- 返却フォーム
- 本追加フォーム
- 会員登録フォーム

この段階では、画面操作が `Controller` を通って業務処理につながることを学びます。

### Phase 4: 画面と設計を整える

- 表示項目を整理する
- プルダウンや入力補助を入れる
- メッセージ表示を分かりやすくする
- View 用のデータ構造を分ける

この段階では、「動く」から「読みやすい、伝わる」に進めます。

### Phase 5: バリデーションと例外処理を入れる

- 空文字チェック
- 不正な入力のエラー表示
- 例外処理の整理
- 必要なら `BindingResult` やバリデーションアノテーションを導入する

この段階で、教材として少し実務に近づきます。

### Phase 6: DB 化する

- `Repository` を JPA ベースに置き換える
- `Entity` を定義する
- H2 などで試せるようにする
- 初期データ投入を整理する

この段階まで行くと、Spring Boot の入門教材としてかなり完成度が高いです。

### Phase 7: PostgreSQL に切り替える

- H2 ではなく PostgreSQL に接続する
- PostgreSQL 用の JDBC driver を追加する
- `application.properties` の datasource 設定を PostgreSQL 用にする
- ローカルの PostgreSQL に接続して既存機能を動かす
- H2 と PostgreSQL の違いを確認する

この段階では、「JPA のコードは大きく変えずに、接続先 DB を変えると何が起きるか」を学びます。

### Phase 8: DB 運用に近い構成へ進める

- Docker Compose で PostgreSQL を起動できるようにする
- `application-h2.properties` / `application-postgres.properties` などで profile を分ける
- `ddl-auto=update` ではなく Flyway でテーブル定義を管理する
- 初期データ投入を Java の起動処理から SQL migration または seed 用処理へ整理する
- テスト用 DB と開発用 DB の扱いを分ける

この段階では、「DB を使える」から「DB 変更を履歴として管理し、環境差分を減らす」ことに進みます。

## どこまでやれば十分か

もし「最初の教材として完成」と言えるラインを 1 つ決めるなら、ここまでで十分です。

- OOP コンソール版がある
- Spring Boot API 版がある
- MVC 画面がある
- 貸出、返却、本追加、会員登録ができる
- 本の貸出状態が見える
- 最低限のテストがある

つまり、今はかなり良いところまで来ています。
ここから先は「理解のための教材」から「少し実務っぽい教材」に広げるフェーズです。

## 次にやるならこの順

優先順位としては次がおすすめです。

1. バリデーションを入れる
2. 画面用 DTO または ViewModel を分ける
3. 例外処理を整理する
4. JPA + H2 で DB 化する
5. PostgreSQL に切り替える
6. Docker / profile / Flyway で DB 運用に近づける
7. 検索や絞り込みを入れる

## 補足

学習順としては、次の見方をすると理解しやすいです。

1. `JavaSample` のコンソール版で責務分割を見る
2. `LibraryService` を見て Spring 版でも業務ルールの中心が同じだと理解する
3. `LibraryPageController` と `templates/library.html` を見て MVC の流れを理解する
4. そのあと `Repository` を DB に変えると何が増えるかを学ぶ
5. H2 で JPA の基本を押さえてから、PostgreSQL や Flyway に進む
