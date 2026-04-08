# Lesson14 Generics And Collections

対応コード: `lesson14_generics_and_collections/Lesson14GenericsAndCollections.java`

## このファイルで学ぶこと

- ジェネリクス
- `List`
- `Set`
- `Map`

## コードの概要

このサンプルでは、`List<String>`、`Set<Integer>`、`Map<String, Integer>` を使って、型安全なコレクションの扱い方を確認します。

`ArrayList` だけでなく、`List` や `Set`、`Map` というよく使う型にも触れます。

## 注目ポイント

### 1. ジェネリクスで入る型を決める

```java
List<String> names = new ArrayList<>();
```

このリストには `String` だけを入れる、という意味です。

### 2. `List` は順番を持つコレクション

```java
names.add("Yuu");
names.add("Aki");
```

追加した順番を保ちながら要素を管理できます。

### 3. `Set` は重複を持たない

```java
ids.add(101);
ids.add(102);
ids.add(101);
```

同じ値を追加しても、`Set` には1回だけ入ります。

### 4. `Map` はキーと値の組み合わせ

```java
scores.put("Math", 90);
System.out.println(scores.get("Math"));
```

キーを使って対応する値を取り出せます。

## 実行するとどうなるか

名前の一覧、重複なしの ID 一覧、そして `Math` に対応する点数が表示されます。

## 自分で試すなら

- `List<Integer>` を作って数値を管理する
- `Set` に同じ値を何度か追加して結果を見る
- `Map` に別の科目を追加する
- `Integer` が `int` とどう違うか調べる
