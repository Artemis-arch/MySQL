import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._


public class main() {
  psvm() {
    val inputpath = args(0)
    val oytputPath = args(1)

    val spark = sparkSession.builder()
      .master()
      .appName()
      .getOrCreate()

    val df = spark.read.parquet(inputpath)

    val df1 = df
      .filiter(col("price")>10)
      .groupBy("ID")
      .agg(avg("price").alias("avg_price"))
      .orderBy(desc("avg.price"))

    df1.write
      .mode("overwrite")   //append
      .parquet(outputpath)

    spark.stop()
  }
}
