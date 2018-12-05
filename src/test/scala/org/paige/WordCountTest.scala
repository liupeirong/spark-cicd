package org.paige

import org.apache.spark.sql.SparkSession
import org.scalatest._

class WordCountTest extends FlatSpec with Matchers with SparkSessionSetup {
    "WordCount for 'abc def' 'hij klm abc'" should "count abc,2 def,1 hij,1 klm,1" in withSparkSession {
    (sparkSession) =>
        val spark2 = sparkSession
        import spark2.implicits._
        val df = List("abc def", "hij klm abc").toDF
        val wc = new WordCount(sparkSession)
        val result = wc.countWords(df)
        result.filter($"value" === "abc").first().getLong(1) should be (2)
        result.filter($"value" === "klm").first().getLong(1) should be (1)
  }
}

trait SparkSessionSetup {
    def withSparkSession(testMethod: (SparkSession) => Any) {
        val hostname = "localhost" //sys.env("HOSTNAME")
        val spark = SparkSession
            .builder
            //ClassCastException without this. See stackoverflow
            //https://stackoverflow.com/questions/39953245/how-to-fix-java-lang-classcastexception-cannot-assign-instance-of-scala-collect
            //works fine in Notebook or spark-submit without this. 
            .config("spark.jars", "target/sparkcicd-0.0.1-SNAPSHOT.jar")
            .master("spark://" + hostname + ":7077")
            .appName("basic spark test")
            .getOrCreate
       try {
           testMethod(spark)
       }
       finally spark.stop
    }
}
