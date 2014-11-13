name := "Lutausis"

version := "0.1-SNAPSHOT"

scalacOptions ++= Seq("-deprecation", "-feature")

scalaVersion := "2.10.4"

crossScalaVersions := Seq("2.10.4", "2.11.1")

libraryDependencies += "org.jsoup" % "jsoup" % "1.7.2"