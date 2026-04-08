# Lesson08 Exceptions

対応コード: `lesson08_exceptions/Lesson08Exceptions.java`

## このファイルで学ぶこと

- 例外
- `try`
- `catch`
- エラー発生時の処理

## コードの概要

このサンプルでは、`10 / 0` によって例外を意図的に発生させ、それを `catch` で受け取っています。

Javaでは、問題が起きそうな処理を安全に扱うために例外処理を使います。

## 注目ポイント

### 1. 危険な処理は `try` に入れる

```java
try {
    int result = 10 / 0;
    System.out.println(result);
}
```

ここでは0で割っているため、通常はエラーになります。

### 2. 例外が起きたら `catch` に移る

```java
catch (ArithmeticException e) {
    System.out.println("例外が発生しました: " + e.getMessage());
}
```

`ArithmeticException` は計算に関する例外です。

### 3. プログラムを急停止させずに対応できる

例外を捕まえることで、エラー内容を表示したり、別の処理へ切り替えたりできます。

## 実行するとどうなるか

0で割ったことで例外が発生し、`例外が発生しました: / by zero` のようなメッセージが表示されます。

## 自分で試すなら

- `10 / 2` にして例外が出ない場合を確認する
- `catch` のメッセージ文言を変える
- `int[] numbers = {1, 2, 3};` を使って配列外アクセスの例外も試す
- `finally` を追加して、最後に必ず動く処理を試す
