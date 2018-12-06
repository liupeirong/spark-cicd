package org.paige

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame

class WordCount(sparkSession: SparkSession) {
    def countWords(df: DataFrame):DataFrame = {
        import sparkSession.implicits._
        val ds = df.as[String]
        return ds.flatMap(w => w.split(' ')).groupBy($"value").count
  }
}