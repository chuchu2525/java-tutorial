# Lesson09 Arrays And Switch

対応コード: `lesson09_arrays_and_switch/Lesson09ArraysAndSwitch.java`

## このファイルで学ぶこと

- 配列
- 要素アクセス
- 配列とループ
- `switch`

## コードの概要

このサンプルでは、文字列の配列に複数の色を入れ、要素の取得と繰り返し表示を行っています。

その後、`switch` を使って数値に応じた分岐も確認しています。

## 注目ポイント

### 1. 配列は同じ型の値をまとめて扱える

```java
String[] colors = {"red", "blue", "green"};
```

`String` の値を3つまとめて管理しています。

### 2. 配列はインデックスで要素を取得する

```java
System.out.println("最初の色: " + colors[0]);
```

最初の要素は `0` 番目です。

### 3. 配列の長さは `length` で取得できる

```java
System.out.println("配列の長さ: " + colors.length);
```

配列の件数を知りたいときに使います。

### 4. `switch` で値ごとの分岐を書ける

```java
switch (menu) {
    case 1:
        System.out.println("コーヒーを選びました");
        break;
    case 2:
        System.out.println("紅茶を選びました");
        break;
    default:
        System.out.println("その他の飲み物を選びました");
        break;
}
```

`if` とは別に、特定の値ごとに処理を分けるときに便利です。

## 実行するとどうなるか

最初の色と配列の長さが表示され、続けて全ての色が順番に表示されます。その後、`menu = 2` に対応する `紅茶を選びました` が表示されます。

## 自分で試すなら

- 配列の中身を自分の好きな色に変える
- 要素を1つ追加して `length` の変化を見る
- `menu` を `1` や `3` に変えて分岐の違いを見る
- 配列と `ArrayList` の書き方の違いを比べる
