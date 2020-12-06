import scala.io.BufferedSource
class Day6 extends AdventDay {

  def countCommonAnswers(groupAnswers: String): Int = {
    groupAnswers
      .split("\n")
      .map(_.toSet)
      .reduce(_.intersect(_))
      .size
  }

  def countTotalDistinctAnswers(groupAnswers: String): Int = {
    groupAnswers.replace("\n", "").toSet.size
  }

  override def readFile(fileBuffer: BufferedSource): Vector[String] = {
    readFileByParagraph(fileBuffer)
  }

  override def runPart1(fileContent: Vector[String]): Int = {
    fileContent.map(countTotalDistinctAnswers(_)).reduce(_ + _)
  }

  override def runPart2(fileContent: Vector[String]): Int = {
    fileContent.map(countCommonAnswers(_)).reduce(_ + _)
  }

}
