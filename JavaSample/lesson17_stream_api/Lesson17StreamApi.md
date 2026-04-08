# Lesson17 Stream Api

対応コード: `lesson17_stream_api/Lesson17StreamApi.java`

## このファイルで学ぶこと

- `stream()`
- `filter`
- `map`
- `collect`

## コードの概要

このサンプルでは、整数の一覧から偶数だけを取り出し、その値を2倍した新しい一覧を作っています。

ループで1つずつ書かなくても、処理の流れをつなげて書けるのが Stream API の特徴です。

## 注目ポイント

### 1. `stream()` でコレクション処理を始める

```java
numbers.stream()
```

一覧に対する加工の流れをここから書き始めます。

### 2. `filter` で条件に合う要素だけ残す

```java
.filter(number -> number % 2 == 0)
```

ここでは偶数だけを残しています。

### 3. `map` で各要素を別の値に変換する

```java
.map(number -> number * 2)
```

残った値を1つずつ2倍しています。

### 4. `collect` で結果を `List` にまとめる

```java
.collect(Collectors.toList());
```

加工結果を新しい一覧として受け取れます。

## 実行するとどうなるか

元の整数一覧と、偶数だけを2倍した新しい一覧が表示されます。

## 自分で試すなら

- 奇数だけを取り出す
- 3倍に変える
- 文字列リストで `map(String::toUpperCase)` を試す
- `for` 文で書いた場合と比較する
