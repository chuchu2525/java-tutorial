# Lesson15 Enum And Final

対応コード: `lesson15_enum_and_final/Lesson15EnumAndFinal.java`

## このファイルで学ぶこと

- `enum`
- `final` 変数
- `final` クラス
- 定数

## コードの概要

このサンプルでは、状態を表す `Status` という `enum` と、定数をまとめた `Constants` クラスを使っています。

さらに、再代入できない `final` 変数も確認できます。

## 注目ポイント

### 1. `enum` は決まった選択肢を表す

```java
enum Status {
    TODO,
    DOING,
    DONE
}
```

文字列で自由に管理するより、決まった値だけを安全に扱えます。

### 2. `final` 変数は再代入できない

```java
final int maxRetry = 3;
```

一度入れた値を変えたくないときに使います。

### 3. `public static final` は定数でよく使う

```java
public static final double TAX_RATE = 0.1;
```

クラス全体で共有する固定値を表します。

## 実行するとどうなるか

現在の状態、最大再試行回数、定数として定義した消費税率が表示されます。

## 自分で試すなら

- `Status` に別の状態を追加する
- `maxRetry` の値を変えてみる
- 別の定数を `Constants` に追加する
- `enum` を `switch` と組み合わせてみる
