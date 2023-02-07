ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "Fufefe TG Bot",
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "3.4.1",
      "com.h2database" % "h2" % "2.1.214",
      "org.postgresql" % "postgresql" % "42.5.3",
      "com.lihaoyi" %% "upickle" % "2.0.0",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1"
    )
  )