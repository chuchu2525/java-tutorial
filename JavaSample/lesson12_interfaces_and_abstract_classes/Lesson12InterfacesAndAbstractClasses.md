# Lesson12 Interfaces And Abstract Classes

対応コード: `lesson12_interfaces_and_abstract_classes/Lesson12InterfacesAndAbstractClasses.java`

## このファイルで学ぶこと

- `interface`
- `implements`
- 抽象クラス
- 抽象メソッド

## コードの概要

このサンプルでは、`Greeter` というインターフェースと、`Device` という抽象クラスを使っています。

`Phone` は `Device` を継承しつつ、`Greeter` も実装しています。

## 注目ポイント

### 1. インターフェースは「できること」の約束

```java
interface Greeter {
    void greet();
}
```

`greet()` を持つことだけを約束しています。

### 2. 抽象クラスは共通処理を持ちながら未完成にもできる

```java
abstract class Device {
    public void powerOn() {
        System.out.println(name + " の電源を入れました");
    }

    public abstract void run();
}
```

共通処理は持たせつつ、`run()` の中身は子クラスに任せています。

### 3. `implements` でインターフェースを実装する

```java
class Phone extends Device implements Greeter {
```

Java では、クラスの継承とインターフェース実装を組み合わせられます。

## 実行するとどうなるか

`MyPhone` の電源を入れる処理が表示され、その後アプリ起動のメッセージとあいさつが表示されます。

## 自分で試すなら

- `Tablet` クラスを追加する
- `Greeter` を実装する別クラスを作る
- `Device` に別の共通メソッドを追加する
- 抽象クラスとインターフェースの違いを言葉で整理する
