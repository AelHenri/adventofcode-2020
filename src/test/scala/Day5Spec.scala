import org.scalatest._
import flatspec._
import matchers._

class Day5Spec extends AnyFlatSpec with should.Matchers {

  def fixture =
    new {
      val day5 = new Day5()
    }

  "seatCodeHelperFunction" should "return (0, 63) given row range (0, 127) and an F row code" in {
      val f = fixture

      f.day5.seatCodeHelperFunction((0, 127), 'F') should be ((0, 63))
  }

  "seatCodeHelperFunction" should "return (32, 63) given row range (0, 63) and an B row code" in {
      val f = fixture

      f.day5.seatCodeHelperFunction((0, 63), 'B') should be ((32, 63))
  }

  "seatCodeHelperFunction" should "return (44, 44) given row range (44, 45) and an F row code" in {
      val f = fixture

      f.day5.seatCodeHelperFunction((44, 45), 'F') should be ((44, 44))
  }

  "seatCode" should "be 567 when the string code is BFFFBBFRRR" in {
      val f = fixture

      f.day5.seatCode("BFFFBBFRRR") should be (567)
  }

  "seatCode" should "be 119 when the string code is FFFBBBFRRR" in {
      val f = fixture

      f.day5.seatCode("FFFBBBFRRR") should be (119)
  }

  "seatCode" should "be 820 when the string code is BBFFBBFRLL" in {
      val f = fixture

      f.day5.seatCode("BBFFBBFRLL") should be (820)
  }

  "findMissingSeat" should "find 42 in list of 39, 40, 41, 43, 44" in {
      val f = fixture
      val seats = Vector(39, 40, 41, 43, 44)

      f.day5.findMissingSeat(seats) should be (42)
  }
}
