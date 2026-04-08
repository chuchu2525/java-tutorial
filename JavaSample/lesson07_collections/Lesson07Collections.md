# Lesson07 Collections

対応コード: `lesson07_collections/Lesson07Collections.java`

## このファイルで学ぶこと

- `ArrayList`
- 要素の追加
- 要素の取得
- 拡張 `for` 文

## コードの概要

このサンプルでは、`ArrayList<String>` を使って科目名の一覧を管理しています。

配列と違って、`ArrayList` はあとから要素を追加しやすいのが特徴です。

## 注目ポイント

### 1. `ArrayList` を使うには `import` が必要

```java
import java.util.ArrayList;
```

標準ライブラリのクラスを使うために宣言しています。

### 2. ジェネリクスで要素の型を決める

```java
ArrayList<String> subjects = new ArrayList<>();
```

このリストには `String` だけを入れる、という意味です。

### 3. 要素の追加と取得

```java
subjects.add("Java");
System.out.println(subjects.get(0));
```

`add` で追加し、`get(0)` で最初の要素を取得します。

### 4. 拡張 `for` 文で順番に取り出せる

```java
for (String subject : subjects) {
    System.out.println(subject);
}
```

リストの中身を1つずつ取り出すときによく使います。

## 実行するとどうなるか

最初の要素として `Java` が表示され、その後リスト内の全要素が順番に表示されます。

## 自分で試すなら

- `subjects.add("CSS");` を追加する
- `subjects.get(1)` を表示する
- `subjects.size()` を使って件数を表示する
- `ArrayList<Integer>` を作って数値を管理する
