name := "play-scala-ddd-sample"
 
version := "1.0" 
      
lazy val `play-scala-ddd-sample` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.12"

libraryDependencies ++= Seq(
  ehcache,
  ws,
  specs2 % Test,
  guice,
  "com.typesafe.play" %% "play-slick" % "5.0.0",
)

unmanagedResourceDirectories in Test +=  baseDirectory.value / "target" / "web" / "public"/ "test"
