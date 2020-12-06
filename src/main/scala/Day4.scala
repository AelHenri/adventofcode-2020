import scala.util.matching.Regex
import scala.annotation.meta.field
import scala.io.BufferedSource

class Day4 extends AdventDay {

  private val PASSPORT_FIELD_RULE = """([a-z]+):(#?[a-z*0-9*]+)[\s]?""".r
  private val FIELDS_TO_CHECK     = Vector("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
  private val CHECK_MAP: Map[String, String => Boolean] = Map(
    ("byr" -> checkBirthYear),
    ("iyr" -> checkIssueYear),
    ("eyr" -> checkExpirationYear),
    ("hgt" -> checkHeight),
    ("hcl" -> checkHairColor),
    ("ecl" -> checkEyeColor),
    ("pid" -> checkPassportId)
  )

  def checkYear(year: String, min: Int, max: Int) = {
    if (year.length != 4) false
    year.toIntOption match {
      case None    => false
      case Some(x) => (x >= min && x <= max)
    }
  }

  def checkBirthYear(field: String): Boolean      = checkYear(field, 1920, 2002)
  def checkIssueYear(field: String): Boolean      = checkYear(field, 2010, 2020)
  def checkExpirationYear(field: String): Boolean = checkYear(field, 2020, 2030)

  def checkHeight(field: String): Boolean = {
    val heightRegex = """([0-9]*)(cm|in)""".r
    field match {
      case heightRegex(height, unit) =>
        (height.toIntOption, unit) match {
          case (Some(x), "cm") => (x >= 150 && x <= 193)
          case (Some(x), "in") => (x >= 59 && x <= 76)
          case _               => false
        }
      case _ => false
    }
  }

  def checkHairColor(field: String): Boolean = {
    val hairColorRegex = """#[0-9a-f]{6}""".r
    field match {
      case hairColorRegex(_*) => true
      case _                  => false
    }
  }

  def checkEyeColor(field: String): Boolean = {
    field match {
      case ("amb" | "blu" | "brn" | "gry" | "grn" | "hzl" | "oth") => true
      case _                                                       => false
    }
  }

  def checkPassportId(field: String): Boolean = {
    val passportIdRegex = """[0-9]{9}""".r
    field match {
      case passportIdRegex(_*) => true
      case _                   => false
    }
  }

  def storePassportFieldsToMap(passportLine: String, fieldRule: Regex): Map[String, String] = {
    fieldRule.findAllMatchIn(passportLine).map(m => (m.group(1), m.group(2))).toMap
  }

  def doesPassportHaveAllFields(passportMap: Map[String, String], fieldsToCheck: Vector[String]): Boolean = {
    val numberOfFieldsToCheck = fieldsToCheck.length
    val actualNumberOfFields = fieldsToCheck
      .filter(passportMap.contains(_))
      .length

    actualNumberOfFields == numberOfFieldsToCheck
  }

  def arePassportFieldsValid(
      passportMap: Map[String, String],
      fieldsCheckingRules: Map[String, String => Boolean]
  ): Boolean = {
    val numberOfFieldsToCheck = fieldsCheckingRules.size
    val actualNumberOfValidFields = fieldsCheckingRules
      .filter(fieldRule =>
        passportMap.get(fieldRule._1) match {
          case None             => false
          case Some(fieldValue) => fieldRule._2.apply(fieldValue)
        }
      )
      .size

    actualNumberOfValidFields == numberOfFieldsToCheck
  }

  override def readFile(fileBuffer: BufferedSource): Vector[String] = {
    readFileByParagraph(fileBuffer)
  }

  override def runPart1(fileContent: Vector[String]): Int = {
    val passportsMap = fileContent.map(storePassportFieldsToMap(_, PASSPORT_FIELD_RULE))

    passportsMap
      .filter(doesPassportHaveAllFields(_, FIELDS_TO_CHECK))
      .length
  }

  override def runPart2(fileContent: Vector[String]): Int = {
    val passportsMap = fileContent.map(storePassportFieldsToMap(_, PASSPORT_FIELD_RULE))

    passportsMap
      .filter(doesPassportHaveAllFields(_, FIELDS_TO_CHECK))
      .filter(arePassportFieldsValid(_, CHECK_MAP))
      .length
  }

}
