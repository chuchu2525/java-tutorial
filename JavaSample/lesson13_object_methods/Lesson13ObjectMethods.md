# Lesson13 Object Methods

対応コード: `lesson13_object_methods/Lesson13ObjectMethods.java`

## このファイルで学ぶこと

- `toString`
- `equals`
- `hashCode`
- コレクションとの関係

## コードの概要

このサンプルでは、`Book` クラスに `toString`、`equals`、`hashCode` を定義しています。

同じ内容の本を2冊作り、比較結果や `HashSet` に入れたときの件数の違いを確認します。

## 注目ポイント

### 1. `toString` を定義すると表示しやすくなる

```java
@Override
public String toString() {
    return "Book{title='" + title + "', price=" + price + "}";
}
```

デバッグ時やログ出力で見やすくなります。

### 2. `equals` は内容が同じかどうかを判定する

```java
System.out.println("equals の結果: " + book1.equals(book2));
```

参照が同じかではなく、中身が同じかを比較できます。

### 3. `hashCode` は `HashSet` や `HashMap` で重要

```java
Set<Book> books = new HashSet<>();
books.add(book1);
books.add(book2);
```

`equals` と `hashCode` をセットで正しく定義しないと、コレクションで意図しない動きになります。

## 実行するとどうなるか

本の内容が文字列として表示され、`equals` の比較結果は `true` になります。さらに、同じ内容の本を2回 `HashSet` に追加しても件数は `1` になります。

## 自分で試すなら

- `price` を変えて比較結果の違いを見る
- `title` だけで比較するように変える
- `HashMap<Book, String>` を試す
- `toString` の表示形式を変える
