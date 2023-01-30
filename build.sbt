ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.1"

lazy val root = (project in file("."))
  .settings(
    name := "Fufefe TG Bot",
    libraryDependencies += "com.lihaoyi" %% "upickle" % "2.0.0"
)