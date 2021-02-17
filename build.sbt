lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.twisleton",
      scalaVersion := "2.13.3"
    )),
    name := "shop"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % Test
