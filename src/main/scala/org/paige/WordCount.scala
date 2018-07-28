package org.paige

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame

class WordCount(spark1: SparkSession) {
    def countWords(df: DataFrame):DataFrame = {
        val spark2 = spark1
        import spark2.implicits._
        val ds = df.as[String]
        return ds.flatMap(w => w.split(' ')).groupBy($"value").count
  }
}