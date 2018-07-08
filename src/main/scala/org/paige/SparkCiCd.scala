package org.paige

import org.apache.spark.sql.SparkSession

object SparkCiCd {
   def main(args: Array[String]): Unit = {
       val spark = SparkSession
        .builder()
        .appName("hello Spark")
        .getOrCreate()

       import spark.implicits._
       val hs = new HelloSpark(spark)
       val df = Seq(12, 23, 36).toDF()
       val result = hs.countElements(df)
       print(result)
   } 
}