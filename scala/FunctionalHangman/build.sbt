name := """FunctionalHangman"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "com.lihaoyi" %% "upickle" % "0.3.4",
  "org.scala-lang.modules" %% "scala-pickling" % "0.10.1",
  "org.specs2" %% "specs2" % "2.4.2" withSources()
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
// routesGenerator := InjectedRoutesGenerator
