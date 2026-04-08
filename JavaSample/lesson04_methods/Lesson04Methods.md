# Lesson04 Methods

対応コード: `lesson04_methods/Lesson04Methods.java`

## このファイルで学ぶこと

- メソッドの定義
- 引数
- 戻り値
- メソッド呼び出し

## コードの概要

このサンプルでは、`greet` と `add` という2つのメソッドを定義しています。

- `greet`: 名前を受け取ってあいさつする
- `add`: 2つの整数を受け取って合計を返す

## 注目ポイント

### 1. メソッドは処理を名前付きでまとめたもの

```java
public static void greet(String name) {
    System.out.println("こんにちは、" + name + "さん");
}
```

同じような処理を何度も書かずに済むようになります。

### 2. 引数はメソッドに渡す入力

```java
greet("Yuu");
```

ここでは `"Yuu"` が `name` に渡されます。

### 3. 戻り値はメソッドから返ってくる結果

```java
public static int add(int a, int b) {
    return a + b;
}
```

`int` を返すので、呼び出し側で変数に受け取れます。

## 実行するとどうなるか

最初にあいさつが表示され、その後 `3 + 5 = 8` が表示されます。

## 自分で試すなら

- `greet("Yamada")` に変える
- `add(10, 20)` に変える
- `subtract` や `multiply` メソッドを追加する
- 名前と年齢を受け取るメソッドを作る
