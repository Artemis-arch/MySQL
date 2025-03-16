// 导入 Apache Spark SQL 的 SparkSession 类，用于创建和管理 Spark 会话
import org.apache.spark.sql.SparkSession
// 导入 Spark SQL 的函数库，包含了各种数据处理函数
import org.apache.spark.sql.functions._

// 定义一个名为 main 的公共类
object main {
  // 程序的入口点，args 是命令行传入的参数数组
  def main(args: Array[String]): Unit = {
    // 从命令行参数中获取输入文件的路径
    val inputPath = args(0)
    // 从命令行参数中获取输出文件的路径
    val outputPath = args(1)

    // 创建一个 SparkSession 实例，用于与 Spark 集群进行交互
    val spark = SparkSession.builder()
      // 设置 Spark 应用程序运行的模式，这里未指定具体模式，可根据实际情况修改
      .master("local[*]")
      // 设置 Spark 应用程序的名称
      .appName("PriceAnalysisApp")
      // 获取已经存在的 SparkSession 实例，如果不存在则创建一个新的实例
      .getOrCreate()

    // 使用 SparkSession 读取指定路径下的 Parquet 文件，并将其加载为一个 DataFrame
    val df = spark.read.parquet(inputPath)

    // 对 DataFrame 进行一系列的数据处理操作
    val df1 = df
      // 过滤出 price 列的值大于 10 的行
      .filter(col("price") > 10)
      // 按照 ID 列进行分组
      .groupBy("ID")
      // 对每个分组计算 price 列的平均值，并将结果命名为 avg_price
      .agg(avg("price").alias("avg_price"))
      // 按照 avg_price 列进行降序排序
      .orderBy(desc("avg_price"))

    // 将处理后的 DataFrame 保存为 Parquet 文件
    df1.write
      // 设置写入模式为覆盖，如果输出路径已存在则覆盖原有文件
      // 插入写用append
      .mode("overwrite") 
      // 指定输出路径
      .parquet(outputPath)

    // 停止 SparkSession，释放资源
    spark.stop()
  }
}
