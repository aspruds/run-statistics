name := """run-statistics"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)

libraryDependencies += "org.jsoup" % "jsoup" % "1.7.2"

libraryDependencies ++= List(
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  "com.h2database" % "h2" % "1.3.175",
  "com.typesafe.play" %% "play-slick" % "0.8.0",
  "com.github.tototoshi" %% "slick-joda-mapper" % "1.2.0",
  "com.github.tototoshi" %% "play-flyway" % "1.1.3"
)

libraryDependencies ++= List(
  "joda-time" % "joda-time" % "2.3",
  "org.joda" % "joda-convert" % "1.6"
)
