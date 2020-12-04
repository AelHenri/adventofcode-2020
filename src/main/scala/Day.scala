import zio.{ZIO}

trait AdventDay {
    def runPart1(fileContent: Vector[String]): Int
    def runPart2(fileContent: Vector[String]): Int
}