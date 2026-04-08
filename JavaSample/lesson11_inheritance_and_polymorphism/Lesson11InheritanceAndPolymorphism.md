# Lesson11 Inheritance And Polymorphism

対応コード: `lesson11_inheritance_and_polymorphism/Lesson11InheritanceAndPolymorphism.java`

## このファイルで学ぶこと

- 継承
- `extends`
- オーバーライド
- 多態性

## コードの概要

このサンプルでは、`Animal` クラスを親クラスとして作り、`Dog` クラスがそれを継承しています。

`Animal animal = new Dog("Pochi");` のように、親クラス型で子クラスのオブジェクトを扱う例も確認できます。

## 注目ポイント

### 1. `extends` で親クラスを引き継ぐ

```java
class Dog extends Animal {
```

親クラスのフィールドやメソッドを使いながら、子クラス独自の振る舞いを追加できます。

### 2. `super(...)` で親クラスのコンストラクタを呼ぶ

```java
Dog(String name) {
    super(name);
}
```

親クラス側の初期化を行っています。

### 3. オーバーライドで振る舞いを変えられる

```java
@Override
public void speak() {
    System.out.println(name + " はワンワンと鳴きます");
}
```

親クラスの `speak()` を子クラス用に上書きしています。
ここで使っている `name` は親クラス側で `protected` にしているため、子クラス（`Dog`）から参照できます。

### 4. 親クラス型で子クラスを扱える

```java
Animal animal = new Dog("Pochi");
```

これが多態性の基本です。同じ型として扱っても、実際に呼ばれるメソッドは子クラス側になります。

## 実行するとどうなるか

まず名前紹介が表示され、その後 `Dog` がオーバーライドした鳴き声が表示されます。

## 自分で試すなら

- `Cat` クラスを追加する
- `Animal` に別の共通メソッドを追加する
- `Dog` 側で `introduce()` もオーバーライドする
- 親クラス型の配列に複数の動物を入れて順番に `speak()` を呼ぶ
