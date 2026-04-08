# Lesson16 Lambda And Functional Interfaces

対応コード: `lesson16_lambda_and_functional_interfaces/Lesson16LambdaAndFunctionalInterfaces.java`

## このファイルで学ぶこと

- ラムダ式
- 関数型インターフェース
- `Predicate`
- `Consumer`

## コードの概要

このサンプルでは、`Predicate<String>` と `Consumer<String>` を使って、ラムダ式の基本形を確認します。

1つは判定処理、もう1つは受け取った値を使う処理です。

## 注目ポイント

### 1. ラムダ式は短く処理を書ける

```java
Predicate<String> isLongName = name -> name.length() >= 4;
```

引数を受け取り、結果を返す小さな処理を簡潔に書けます。

### 2. `Predicate` は true / false を返す

```java
isLongName.test(name);
```

条件判定に使われる代表的な関数型インターフェースです。

### 3. `Consumer` は受け取った値を使って処理する

```java
Consumer<String> printer = text -> System.out.println("出力: " + text);
```

戻り値がない処理を表したいときに使います。

## 実行するとどうなるか

`Yuu` が4文字以上かどうかの判定結果が表示され、その後に `Yuu` が出力されます。

## 自分で試すなら

- 名前を別の文字列に変える
- 文字数条件を `>= 3` に変える
- `Consumer<Integer>` を作って数値を表示する
- `Predicate<Integer>` で偶数判定を作る
