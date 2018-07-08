package org.paige

import org.apache.spark.sql.SparkSession
import org.scalatest._

class HelloSparkTest extends FlatSpec with Matchers with SparkSessionSetup {
    "HelloSpark" should "count the right number of elements" in withSparkSession {
        (sparkSession) =>
        val spark2 = sparkSession
        import spark2.implicits._

        val df = Seq(10, 20, 30).toDF()
        val hs = new HelloSpark(sparkSession)
        hs.countElements(df) should be (3)
    }
}

trait SparkSessionSetup {
    def withSparkSession(testMethod: (SparkSession) => Any) {
        val spark = SparkSession
            .builder()
            .master("local")
            .appName("basic spark test")
            .getOrCreate()
       try {
           testMethod(spark)
       }
       finally spark.stop()
    }
}
