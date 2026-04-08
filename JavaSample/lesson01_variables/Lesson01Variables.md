# Lesson01 Variables

対応コード: `lesson01_variables/Lesson01Variables.java`

## このファイルで学ぶこと

- 変数の宣言
- 基本的な型
- 文字列と値の出力
- 変数の再代入

## コードの概要

このサンプルでは、`name`、`age`、`height`、`likesJava` という4つの変数を使って、Javaの基本的な型を確認しています。

- `String`: 文字列
- `int`: 整数
- `double`: 小数
- `boolean`: 真偽値

その後、`System.out.println(...)` で値を表示し、`age = age + 1;` で変数の値を更新しています。

## 注目ポイント

### 1. 変数は「型 + 名前 + 値」で書く

```java
String name = "Yuu";
int age = 20;
```

Javaでは、変数を作るときに型を明示します。

### 2. 文字列と他の値は `+` でつなげられる

```java
System.out.println("年齢: " + age);
```

文字列と数値を `+` でつなぐと、表示用の文字列として結合されます。

### 3. 変数は後から更新できる

```java
age = age + 1;
```

最初に入れた値を、後で別の値に変えられます。これを再代入と呼びます。

## 実行するとどうなるか

名前、年齢、身長、Javaが好きかどうかが表示され、最後に1つ増えた年齢が出力されます。

## 自分で試すなら

- `name` を自分の名前に変える
- `age` を別の数値にする
- `likesJava` を `false` にして表示の違いを見る
- `int` と `double` の違いを確認する
