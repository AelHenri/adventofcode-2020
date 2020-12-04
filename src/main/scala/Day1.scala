class Day1 extends AdventDay {
    override def runPart1(numbers: Vector[String]): Int = {
        val intNumbers = numbers.map(_.toInt)
        val mapOfNumbers = intNumbers.map(x => (x, 2020 - x)).toMap
        val (x, y) = mapOfNumbers.filter(num => mapOfNumbers.contains(num._2)).head
        x * y
    }

    override def runPart2(numbers: Vector[String]): Int = {
        val intNumbers = numbers.map(_.toInt)
        val numbersSet = intNumbers.toSet

        /* This is what is behind the following for comprehension, which is syntactic sugar
        val mapOfNumbers: Map[(Int, Int), Int] = intNumbers.flatMap(x => 
            intNumbers.map(y => ((x, y), 2020 - x - y))
        ).toMap.filter(elem => elem._1._1 != elem._1._2)
        */
        val mapOfNumbers = (for {
            x <- intNumbers;
            y <- intNumbers;
            if x != y
        } yield ((x, y), 2020 - x - y)).toMap

        val ((x, y), z) = mapOfNumbers.filter(num => numbersSet.contains(num._2)).head

        println(s"$x, $y, $z")
        x * y * z
    }
}