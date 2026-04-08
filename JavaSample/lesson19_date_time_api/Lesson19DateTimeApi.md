# Lesson19 Date Time Api

対応コード: `lesson19_date_time_api/Lesson19DateTimeApi.java`

## このファイルで学ぶこと

- `LocalDate`
- `LocalDateTime`
- `DateTimeFormatter`
- `Duration`

## コードの概要

このサンプルでは、今日の日付、1週間後の日付、指定した日時、学習時間を扱っています。

日付や時刻を文字列で直接扱わず、Java 標準の日時 API を使う基本を確認します。

## 注目ポイント

### 1. `LocalDate` は日付だけを表す

```java
LocalDate today = LocalDate.now();
LocalDate nextWeek = today.plusDays(7);
```

日付計算もメソッドで分かりやすく書けます。

### 2. `LocalDateTime` は日付と時刻をまとめて表す

```java
LocalDateTime meeting = LocalDateTime.of(2026, 4, 10, 14, 30);
```

年月日と時刻を1つの値として扱えます。

### 3. `DateTimeFormatter` で表示形式を整える

```java
meeting.format(formatter)
```

見やすい形式で日時を表示できます。

### 4. `Duration` は時間の長さを表す

```java
Duration studyTime = Duration.ofMinutes(90);
```

日時そのものではなく、経過時間を表したいときに使います。

## 実行するとどうなるか

今日の日付、1週間後の日付、フォーマット済みの会議日時、学習時間の分数が表示されます。

## 自分で試すなら

- `plusDays(30)` に変える
- 別の日時フォーマットを試す
- `Duration.ofHours(2)` を試す
- 誕生日までの日数を計算するコードを考える
