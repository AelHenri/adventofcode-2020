import scala.io.BufferedSource
trait AdventDay {
  def runPart1(fileContent: Vector[String]): Int
  def runPart2(fileContent: Vector[String]): Int

  def readFile(fileBuffer: BufferedSource): Vector[String] = {
    readFileByLine(fileBuffer)
  }

  protected def readFileByLine(fileBuffer: BufferedSource): Vector[String] = {
    fileBuffer.getLines().toVector
  }

  protected def readFileByParagraph(fileBuffer: BufferedSource): Vector[String] = {
    fileBuffer
      .mkString
      .split("\n\n")
      .toVector
  }
}
