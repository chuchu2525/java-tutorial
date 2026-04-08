# Lesson05 Classes And Objects

対応コード: `lesson05_classes_and_objects/Lesson05ClassesAndObjects.java`

## このファイルで学ぶこと

- クラス
- オブジェクト
- フィールド
- インスタンスメソッド

## コードの概要

このサンプルでは、`Person` クラスを定義し、その設計図から `person` というオブジェクトを作っています。

`Person` は次の情報を持っています。

- `name`
- `age`

そして、自己紹介する `introduce()` メソッドも持っています。

## 注目ポイント

### 1. クラスは設計図

```java
class Person {
    String name;
    int age;
}
```

どんなデータを持ち、どんな振る舞いをするかをまとめて定義します。

### 2. `new` でオブジェクトを作る

```java
Person person = new Person();
```

ここで実体が作られます。これをインスタンス化と呼びます。

### 3. フィールドに値を入れてメソッドを呼ぶ

```java
person.name = "Yuu";
person.age = 20;
person.introduce();
```

オブジェクトごとに状態を持ち、その状態を使って振る舞います。

## 実行するとどうなるか

`私は Yuu、20歳です。` と表示されます。

## 自分で試すなら

- `Person` を2人分作る
- `introduce()` の表示内容を変える
- `hobby` フィールドを追加する
- `introduce()` に趣味も含める
