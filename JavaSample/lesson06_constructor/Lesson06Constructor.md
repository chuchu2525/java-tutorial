# Lesson06 Constructor

対応コード: `lesson06_constructor/Lesson06Constructor.java`

## このファイルで学ぶこと

- コンストラクタ
- `this`
- オブジェクト生成時の初期化

## コードの概要

このサンプルでは、`Student` クラスにコンストラクタを用意して、オブジェクトを作ると同時に `name` と `grade` を設定しています。

## 注目ポイント

### 1. コンストラクタはクラス名と同じ名前

```java
Student(String name, int grade) {
    this.name = name;
    this.grade = grade;
}
```

戻り値の型は書きません。オブジェクト作成時の初期化専用です。

### 2. `this` は「このオブジェクト自身」

```java
this.name = name;
```

左側の `this.name` はフィールド、右側の `name` は引数です。

### 3. 値を入れ忘れにくくなる

```java
Student student = new Student("Sato", 2);
```

生成時点で必要な値をまとめて渡せるので、安全でわかりやすくなります。

## 実行するとどうなるか

`名前: Sato, 学年: 2` と表示されます。

## 自分で試すなら

- 学生を2人作る
- `schoolName` フィールドを追加する
- コンストラクタの引数を増やす
- `showProfile()` の表示形式を変える
