class Day3 extends AdventDay {

  def findPositionFromSlope(lineLength: Int, verticalPos: Int, slope: (Int, Int)): Option[Int] = {
    if (verticalPos % slope._2 != 0) None
    else {
      Some(slope._1 * verticalPos / slope._2 % lineLength)
    }
  }

  def countTrees(mapLines: Vector[String], slope: (Int, Int)): Int = {
    mapLines.zipWithIndex
      .map(line => (line._1, findPositionFromSlope(line._1.length, line._2, slope)))
      .filter {
        case (line, Some(pos)) => line(pos) == '#'
        case _                 => false
      }
      .length
  }

  override def runPart1(mapLines: Vector[String]): Int = {
    val slope = (3, 1)
    countTrees(mapLines, slope)
  }

  override def runPart2(mapLines: Vector[String]): Int = {
    val slopes = Vector(
      (1, 1),
      (3, 1),
      (5, 1),
      (7, 1),
      (1, 2)
    )

    slopes.map(countTrees(mapLines, _)).reduceLeft(_ * _)
  }
}
