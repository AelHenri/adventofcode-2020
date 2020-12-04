import scala.util.matching.Regex
class Day2 extends AdventDay {
  val PASSWORD_RULE = """([0-9]+)-([0-9]+)\s([a-z]):\s([a-z]*)""".r

  def decomposeLine(line: String, rule: Regex): Option[(Int, Int, Char, String)] =
    line match {
      case rule(min, max, char, password) => Some((min.toInt, max.toInt, char.head, password))
      case _                              => None
    }

  def isPasswordConform(min: Int, max: Int, char: Char, password: String): Boolean = {
    val charCount = password.count(_ == char)
    if (charCount >= min && charCount <= max)
      true
    else
      false
  }

  def isPasswordConformPart2(pos1: Int, pos2: Int, char: Char, password: String): Boolean = {
      val pos1Char = password.lift(pos1-1)
      val pos2char = password.lift(pos2-1)

      (pos1Char, pos2char) match {
          case (Some(a), Some(b)) => (a == char ^ b == char)
          case _ => false
      }
  }

  override def runPart1(passwords: Vector[String]): Int = {
    val decomposedLines = passwords.map(decomposeLine(_, PASSWORD_RULE))
    decomposedLines.filter {
      case Some((min: Int, max: Int, char: Char, password: String)) =>
        isPasswordConform(min, max, char, password)
      case _ => false
    }.length
  }

  override def runPart2(passwords: Vector[String]): Int = {
    val decomposedLines = passwords.map(decomposeLine(_, PASSWORD_RULE))
    decomposedLines.filter {
      case Some((min: Int, max: Int, char: Char, password: String)) =>
        isPasswordConformPart2(min, max, char, password)
      case _ => false
    }.length
  }
}
