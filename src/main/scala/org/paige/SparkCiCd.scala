package org.paige

import org.apache.spark.sql.SparkSession

object SparkCiCd {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("Spark word count")
      .getOrCreate

      val fileLocation = args(0)
      val df = spark.read.text(fileLocation)
      val wc = new WordCount(spark).countWords(df)
      wc.show
   } 
}