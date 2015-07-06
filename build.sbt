import com.typesafe.sbt.packager.archetypes.ServerLoader.SystemV
name := """run-statistics"""

version := "1.0-SNAPSHOT"

routesGenerator := InjectedRoutesGenerator

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies += "org.jsoup" % "jsoup" % "1.7.2"

libraryDependencies ++= List(
  cache
)

libraryDependencies ++= List(
  "com.typesafe.slick" %% "slick" % "3.0.0",
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "com.typesafe.play" %% "play-slick" % "1.0.0",
  "com.github.tototoshi" %% "slick-joda-mapper" % "2.0.0",
  "org.flywaydb" %% "flyway-play" % "2.0.1"
)

libraryDependencies ++= List(
  "joda-time" % "joda-time" % "2.7",
  "org.joda" % "joda-convert" % "1.7"
)
