# Lesson10 Access Modifiers And Capsulation

対応コード: `lesson10_access_modifiers_and_capsulation/Lesson10AccessModifiersAndCapsulation.java`

## このファイルで学ぶこと

- アクセス修飾子
- `private`
- `public`
- `protected`（概要）
- カプセル化

## コードの概要

このサンプルでは、`BankAccount` クラスの `owner` と `balance` を `private` にして、外から直接変更されないようにしています。

値の取得は getter、更新は `deposit` メソッドを通して行います。

## 注目ポイント

### 1. `private` を使うと外から直接触れなくなる

```java
private String owner;
private int balance;
```

フィールドを勝手に変更されないようにできます。

### 2. `public` メソッドで必要な操作だけ公開する

```java
public int getBalance() {
    return balance;
}
```

公開してよい操作だけを外に見せるのが基本です。

### 3. 更新処理をメソッドにまとめるとルールを守らせやすい

```java
public void deposit(int amount) {
    if (amount > 0) {
        balance += amount;
    }
}
```

ここでは、0以下の値は入金しないというルールを入れています。

### 4. `protected` は「継承先から使える」修飾子

- `protected` は、同じパッケージ内に加えて、サブクラス（子クラス）から参照できます
- `private` はそのクラス内だけ、`public` はどこからでも参照できます

この lesson では主に `private` と `public` でカプセル化を学び、`protected` の本格的な使い方は継承の lesson（`lesson11` 以降）で確認すると理解しやすいです。

## 実行するとどうなるか

`Yuu` の口座に `500` 円を入金した結果として、口座名義と更新後の残高が表示されます。

## 自分で試すなら

- `deposit(0)` や `deposit(-100)` を試す
- `withdraw` メソッドを追加する
- getter の代わりに `showBalance()` メソッドを作る
- `owner` を `private` のまま安全に扱う方法を考える
