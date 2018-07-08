package org.paige

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

class HelloSpark(spark1: SparkSession) {
    def countElements(df: DataFrame):Long = {
        val spark2 = spark1
        import spark2.implicits._
        return df.count()
    }
}