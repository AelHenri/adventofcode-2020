import org.scalatest._
import flatspec._
import matchers._

class Day6Spec extends AnyFlatSpec with should.Matchers {

  def fixture =
    new {
      val day6 = new Day6()
    }

  "Day 6 part 1 runner" should "find 11 questions to which anyone answered yes" in {
    val f = fixture
    val groupAnswers = Vector(
      "abc",
      """a
b
c""",
      """ab
ac""",
      """a
a
a
a""",
      "b"
    )

    f.day6.runPart1(groupAnswers) should be (11)
  }

  "Day 6 part 2 runner" should "find 6 questions to which anyone answered yes" in {
    val f = fixture
    val groupAnswers = Vector(
      "abc",
      """a
b
c""",
      """ab
ac""",
      """a
a
a
a
a""",
      "b"
    )

    f.day6.runPart2(groupAnswers) should be (6)
  }
}
