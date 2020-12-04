import org.scalatest._
import flatspec._
import matchers._

class Day3Spec extends AnyFlatSpec with should.Matchers {

  "findPositionFromSlope" should "return a horizontal position at 0 given a slope of (3, 1) and a vertical position of 0" in {
    val day3        = new Day3()
    val lineLength  = 31
    val slope       = (3, 1)
    val verticalPos = 0

    day3.findPositionFromSlope(lineLength, verticalPos, slope) should be(Some(0))
  }

  "findPositionFromSlope" should "return a horizontal position at 9 given a slope of (3, 1) and a vertical position of 3" in {
    val day3        = new Day3()
    val lineLength  = 31
    val slope       = (3, 1)
    val verticalPos = 3

    day3.findPositionFromSlope(lineLength, verticalPos, slope) should be(Some(9))
  }

  "findPositionFromSlope" should "return a horizontal position at 2 given a slope of (3, 1) and a vertical position of 11" in {
    val day3        = new Day3()
    val lineLength  = 31
    val slope       = (3, 1)
    val verticalPos = 11

    day3.findPositionFromSlope(lineLength, verticalPos, slope) should be(Some(2))
  }

  "findPositionFromSlope" should "return no horizontal position given a slope of (3, 2) and a vertical position of 1" in {
    val day3        = new Day3()
    val lineLength  = 31
    val slope       = (3, 2)
    val verticalPos = 1

    day3.findPositionFromSlope(lineLength, verticalPos, slope) should be(None)
  }

  "findPositionFromSlope" should "return a horizontal position of 1 given a slope of (3, 2) and a vertical position of 2" in {
    val day3        = new Day3()
    val lineLength  = 31
    val slope       = (1, 2)
    val verticalPos = 2

    day3.findPositionFromSlope(lineLength, verticalPos, slope) should be(Some(1))
  }

  "countTrees" should "find 7 trees given a slope of (3, 1)" in {
    val day3  = new Day3()
    val slope = (3, 1)
    val treemap = Vector(
      "..##.......",
      "#...#...#..",
      ".#....#..#.",
      "..#.#...#.#",
      ".#...##..#.",
      "..#.##.....",
      ".#.#.#....#",
      ".#........#",
      "#.##...#...",
      "#...##....#",
      ".#..#...#.#"
    )

    day3.countTrees(treemap, slope) should be(7)
  }

  "countTrees" should "find 2 trees given a slope of (1, 2)" in {
    val day3  = new Day3()
    val slope = (1, 2)
    val treemap = Vector(
      "..##.......",
      "#...#...#..",
      ".#....#..#.",
      "..#.#...#.#",
      ".#...##..#.",
      "..#.##.....",
      ".#.#.#....#",
      ".#........#",
      "#.##...#...",
      "#...##....#",
      ".#..#...#.#"
    )

    day3.countTrees(treemap, slope) should be(2)
  }
}
