# Lesson20 File Io Basics

対応コード: `lesson20_file_io_basics/Lesson20FileIoBasics.java`

## このファイルで学ぶこと

- `Path`
- `Files.writeString`
- `Files.readString`
- ファイル操作と例外処理

## コードの概要

このサンプルでは、`sample.txt` に文字列を書き込み、その内容を読み戻して表示しています。

`java.nio.file` の API を使うと、ファイル操作を比較的シンプルに書けます。

## 注目ポイント

### 1. `Path` でファイルの場所を表す

```java
Path path = Path.of("JavaSample/lesson20_file_io_basics/sample.txt");
```

文字列だけでパスを扱うより意図が明確になります。

### 2. `Files.writeString` で文字列を書き込める

```java
Files.writeString(path, "Java lesson sample\n");
```

簡単なテキストファイルなら短く書けます。

### 3. `Files.readString` で内容を読み込める

```java
String content = Files.readString(path);
```

ファイル全体を文字列として読み取れます。

### 4. ファイル操作では例外処理が大切

```java
} catch (IOException e) {
    System.out.println("ファイル操作に失敗しました: " + e.getMessage());
}
```

ファイルが読めない、書けない場合に備えておきます。

## 実行するとどうなるか

`sample.txt` に文字列が書き込まれ、その内容が読み出されて表示されます。

## 自分で試すなら

- 書き込む文字列を変える
- 別のファイル名に変える
- 複数行を書き込む
- 読み込んだ文字列に加工を加えてから表示する
