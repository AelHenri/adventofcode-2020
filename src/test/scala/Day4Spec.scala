import org.scalatest._
import flatspec._
import matchers._

class Day4Spec extends AnyFlatSpec with should.Matchers {

  val PASSPORT_FIELD_RULE = """([a-z]+):(#?[a-z*0-9*]+)[\s]?""".r
  val FIELDS_TO_CHECK     = Vector("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

  def fixture =
    new {
      val day4 = new Day4()
    }

  "storePassportFields" should "return a map of fields" in {
    val f = fixture
    val passportLine =
      """iyr:2010 ecl:gry hgt:181cm pid:591597745 byr:1920 hcl:#6b5442 eyr:2029 cid:123"""
    val expected = Map(
      ("iyr", "2010"),
      ("ecl", "gry"),
      ("hgt", "181cm"),
      ("pid", "591597745"),
      ("byr", "1920"),
      ("hcl", "#6b5442"),
      ("eyr", "2029"),
      ("cid", "123")
    )

    f.day4.storePassportFieldsToMap(passportLine, PASSPORT_FIELD_RULE) should be(expected)
  }

  "checkBirthYear" should "validate 2002 and invalidate 2003" in {
    val f = fixture

    f.day4.checkBirthYear("2002") should be (true)
    f.day4.checkBirthYear("2003") should be (false)
  }

  "checkHeight" should "validate or invalidate based on unit presence and height value" in {
    val f = fixture

    f.day4.checkHeight("60in") should be (true)
    f.day4.checkHeight("190cm") should be (true)
    f.day4.checkHeight("190in") should be (false)
    f.day4.checkHeight("190") should be (false)
  }

  "checkHairColor" should "validate #123abc" in {
    val f = fixture

    f.day4.checkHairColor("#123abc") should be (true)
  }

  "checkHairColor" should "invalidate #123abz" in {
    val f = fixture

    f.day4.checkHairColor("#123abz") should be (false)
  }

  "checkHairColor" should "invalidate 123abc" in {
    val f = fixture

    f.day4.checkHairColor("123abc") should be (false)
  }

  "checkEyeColor" should "validate based on string value" in {
    val f = fixture

    f.day4.checkEyeColor("brn") should be (true)
    f.day4.checkEyeColor("wat") should be (false)
  }

  "checkPassportId" should "validate 000000001" in {
    val f = fixture

    f.day4.checkPassportId("000000001") should be (true)
  }

  "checkPassportId" should "invalidate 0123456789" in {
    val f = fixture

    f.day4.checkPassportId("0123456789") should be (false)
  }

  "Day 4 part 1 runner" should "find 2 valid passports" in {
    val f = fixture
    val passports = Vector(
      """ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm""",
      """iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929""",
      """hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm""",
      """hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in"""
    )

    f.day4.runPart1(passports) should be(2)
  }

  "Day 4 part 2 runner" should "find 0 valid passports" in {
    val f = fixture
    val passports = Vector(
      """eyr:1972 cid:100
hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926""",
"""iyr:2019
hcl:#602927 eyr:1967 hgt:170cm
ecl:grn pid:012533040 byr:1946""",
"""hcl:dab227 iyr:2012
ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277""",
"""hgt:59cm ecl:zzz
eyr:2038 hcl:74454a iyr:2023
pid:3556412378 byr:2007"""
    )

    f.day4.runPart2(passports) should be (0)
  }


  "Day 4 part 2 runner" should "find 4 valid passports" in {
    val f = fixture
    val passports = Vector(
      """pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
hcl:#623a2f""",
"""eyr:2029 ecl:blu cid:129 byr:1989
iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm""",
"""hcl:#888785
hgt:164cm byr:2001 iyr:2015 cid:88
pid:545766238 ecl:hzl
eyr:2022""",
"""iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719"""
    )

    f.day4.runPart2(passports) should be (4)
  }
}
