ThisBuild / scalaVersion     := "2.13.3"
ThisBuild / version          := "0.0.1"

lazy val sample = (project in file("."))
  .settings(
    name := "Advent Of Code 2020 Scala",
    scalacOptions += "-deprecation",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % Test,
    libraryDependencies += "dev.zio" %% "zio" % "1.0.3"
  )