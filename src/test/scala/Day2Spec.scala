import org.scalatest._
import flatspec._
import matchers._

class Day2Spec extends AnyFlatSpec with should.Matchers {
  val PASSWORD_RULE = """([0-9]+)-([0-9]+)\s([a-z]):\s([a-z]*)""".r

  "A password line" should "be decomposed in a min, a max, a character and a password" in {
    val day2 = new Day2()
    val line = "11-16 l: llllqllllllllflq"

    day2.decomposeLine(line, PASSWORD_RULE) should be(Some((11, 16, 'l', "llllqllllllllflq")))
  }

  "An empty password line" should "be decomposed into None" in {
    val day2 = new Day2()

    day2.decomposeLine("", PASSWORD_RULE) should be(None)
  }

  "A Day 2 Part 1 runner" should "return 2 conform passwords out of 3" in {
    val day2 = new Day2()
    val passwords = Vector(
      "1-3 a: abcde",
      "1-3 b: cdefg",
      "2-9 c: ccccccccc"
    )
    day2.runPart1(passwords) should be(2)
  }

  "A Day 2 Part 2 runner" should "return 1 conform passwords out of 3" in {
    val day2 = new Day2()
    val passwords = Vector(
      "1-3 a: abcde",
      "1-3 b: cdefg",
      "2-9 c: ccccccccc"
    )
    day2.runPart2(passwords) should be(1)
  }
}
