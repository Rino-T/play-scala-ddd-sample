# Play Framework Scala Clean Architecture


下記を元にScalaで書き直してみる。
* https://github.com/nrslib/play-clean-java


## 環境

* [Play Framework](https://www.playframework.com/) 2.8.2
* [Scala](https://www.scala-lang.org/) 2.12.12
* Java 11

## 実行方法

ローカル実行用のDBを立てるのにDockerを利用しています。

```shell script
cd docker
docker-compose up
```

MariaDBが立ち上がります。

Webアプリの実行

```shell script
sbt run
```

## サンプル機能

- [x] UserのCRUD
- [ ] ログイン機能
