class Day5 extends AdventDay {

  def seatCodeHelperFunction(range: (Int, Int), seatCodeChar: Char): (Int, Int) = {
    seatCodeChar match {
      case ('F' | 'L') if (range._1 + 1 == range._2) => (range._1, range._1)
      case ('B' | 'R') if (range._1 + 1 == range._2) => (range._2, range._2)
      case ('F' | 'L')                               => (range._1, range._1 + (range._2 - range._1) / 2)
      case ('B' | 'R')                               => (range._1 + (range._2 - range._1) / 2 + 1, range._2)
      case _                                         => range
    }
  }

  def foldedNumber(range: (Int, Int), seatCode: String): Int = {
    seatCode.foldLeft(range)(seatCodeHelperFunction)._1
  }

  def seatCode(stringCode: String): Int = {
    val frontBackCode = stringCode.slice(0, 7)
    val leftRightCode = stringCode.slice(7, stringCode.length)
    val row           = foldedNumber((0, 127), frontBackCode)
    val column        = foldedNumber((0, 7), leftRightCode)

    row * 8 + column
  }

  def seatCodeBinaryVersion(stringCode: String): Int = {
    val binaryCode = stringCode
      .map(char =>
        char match {
          case ('F' | 'L') => '0'
          case ('B' | 'R') => '1'
        }
      )
    Integer.parseInt(binaryCode, 2)
  }

  def findMissingSeat(seatIds: Vector[Int]): Int = {
    val seatIdsSet = seatIds.toSet
    seatIdsSet.filter(id => !seatIds.contains(id + 1) && seatIds.contains(id + 2)).head + 1
  }

  override def runPart1(fileContent: Vector[String]): Int = {
    fileContent.map(seatCodeBinaryVersion(_)).max
  }

  override def runPart2(fileContent: Vector[String]): Int = {
    val seatIds = fileContent.map(seatCode(_))
    findMissingSeat(seatIds)
  }
}
