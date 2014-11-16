name := """run-statistics"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)

libraryDependencies += "org.jsoup" % "jsoup" % "1.7.2"

libraryDependencies += "com.github.tototoshi" %% "play-flyway" % "1.1.3"
