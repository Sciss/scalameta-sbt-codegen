lazy val rootProj = (project in file("."))
  .aggregate(codegen, app)
  .enablePlugins(CrossPerProjectPlugin)

lazy val codegen = project.settings(
  scalaVersion := "2.10.6",
  sbtPlugin := true,
  crossScalaVersions := Seq("2.10.6"),
  libraryDependencies +=
    "org.scala-refactoring" % "org.scala-refactoring.library_2.10" % "0.8.0"
)

lazy val app = project.settings(
  scalaVersion := "2.11.8",
  crossScalaVersions := Seq("2.12.1", "2.11.8", "2.10.6"),
  sourceGenerators in Compile += Def.taskDyn {
    val outFile = sourceManaged.value / "mycodegen" / "Generated.scala"
    Def.task {
      (run in codegen in Compile)
        .toTask(" " + outFile.getAbsolutePath)
        .value
      Seq(outFile)
    }
  }.taskValue
)
