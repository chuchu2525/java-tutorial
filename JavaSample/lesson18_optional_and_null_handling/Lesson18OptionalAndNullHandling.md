# Lesson18 Optional And Null Handling

対応コード: `lesson18_optional_and_null_handling/Lesson18OptionalAndNullHandling.java`

## このファイルで学ぶこと

- `Optional`
- `Optional.empty()`
- `orElse`
- `null` の代わりの表現

## コードの概要

このサンプルでは、ニックネームがある人とない人を `Optional<String>` で表しています。

値がない可能性を、`null` ではなく `Optional` で明示しています。

## 注目ポイント

### 1. `Optional` は値があるかもしれないことを表す

```java
public static Optional<String> findNickname(String name)
```

戻り値の型を見るだけで、値がない場合があると分かります。

### 2. 値がないときは `Optional.empty()` を返す

```java
return Optional.empty();
```

`null` を直接返すより意図が明確です。

### 3. `orElse` で値がないときの代替を決める

```java
nickname2.orElse("未設定")
```

値がなければ `"未設定"` が使われます。

## 実行するとどうなるか

`Yuu` にはニックネームが表示され、`Aki` には `未設定` が表示されます。

## 自分で試すなら

- 別の名前にもニックネームを追加する
- `orElse` の文字列を変える
- `isPresent()` を使って値の有無を確認する
- `null` を返す書き方と何が違うか比べる
