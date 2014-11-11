### How to run this project.

```scala
$ sbt run
```

実行する前に’which sbt’して呼んでいるファイルを以下のように編集。

```
-XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=256m
```

こうしておこないとPermgen heapspace errorで怒られる。

実行は以下のようにする。

```
/opt/local/repos/reading/spark/bin/spark-submit --class SocketStreaming /opt/local/repos/spark/streaming/target/scala-2.10/socket-project_2.10-1.0.jar
```
