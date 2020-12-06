import scala.io.Source

object Runner extends App {

  val usage = """
    Usage: sbt "run dayNumber"
  """
  val dayNumber = args.lift(0).map(_.toInt)

  val adventCalendar: Vector[AdventDay] = Vector(
    new Day1(),
    new Day2(),
    new Day3(),
    new Day4(),
    new Day5()
  )

  dayNumber match {
    case None => println(usage)  
    case Some(dn) => {
      val currentDay = adventCalendar.lift(dn - 1)
      currentDay match {
        case None => println(s"The ${dn}th of December is not yet implemented !")
        case Some(cd) => {
          val fileName = s"day${dn}.txt"
          val fileBuffer = Source.fromResource(fileName)
          val fileContent = fileBuffer.getLines.toVector
          fileBuffer.close()
          
          val part1 = cd.runPart1(fileContent)
          val part2 = cd.runPart2(fileContent)

          println(s"""
            Part 1 answer: $part1
            Part 2 answer: $part2
          """)
        }
      }
    }
  }
}
