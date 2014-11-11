import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.storage.StorageLevel

object SocketStreaming {
  def main(args: Array[String]) {
    val sparkConf = new SparkConf()
                        .setMaster("local[*]")
                        .setAppName("SocketStreaming")
                        .setJars(List("target/scala-2.10/socket-project_2.10-1.0.jar"))

    val streamingContext = new StreamingContext(sparkConf, Seconds(5))

    val lines = streamingContext.socketTextStream("localhost", 9999, StorageLevel.MEMORY_AND_DISK_SER)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)
    wordCounts.print()
    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
