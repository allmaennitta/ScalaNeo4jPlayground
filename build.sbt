import sbt.Keys._
import sbt.Resolver

lazy val root = (project in file(".")).
  enablePlugins(TomcatPlugin).
  settings(
    name := "ConceptMap",
    version := "0.9",
    scalaVersion := "2.12.4",
    sbtVersion := "1.0.3"
  )

val vTomcat: String = "8.5.20"
val vScalaTest: String = "3.0.0"
val vAkka: String = "2.4.10"
val vSpringBoot: String = "1.5.7.RELEASE"
val vJackson: String = "2.8.10"
val vNeo4J: String = "4.2.8.RELEASE"

resolvers ++= Seq(
  "Local Maven Repository" at "file:///home/ingo/.m2/repository",
  Resolver.url("spring_release", url("https://repo.spring.io/libs-release"))
)

libraryDependencies ++= Seq(
//AKKA, parallel actors
  //  "com.typesafe.akka" %% "akka-actor" % vAkka,
  //  "com.typesafe.akka" %% "akka-testkit" % vAkka % "test",

  //WEB
  "org.springframework.boot" % "spring-boot-starter-web" % vSpringBoot,
  "org.springframework.boot" % "spring-boot-starter-tomcat" % vSpringBoot,
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "org.springframework.hateoas" % "spring-hateoas" % "0.24.0.RELEASE",

  //JACKSON FOR YAML
  "com.fasterxml.jackson.core" % "jackson-core" % vJackson,
  "com.fasterxml.jackson.core" % "jackson-annotations" % vJackson,
  "com.fasterxml.jackson.core" % "jackson-databind" % vJackson,
  "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % vJackson,

  //DATA
  "org.springframework.boot" % "spring-boot-starter-data-neo4j" % vSpringBoot,
//  "org.hsqldb" % "hsqldb" % "2.3.1",
//  "org.hibernate" % "hibernate-validator" % "5.3.5.Final",
//  "org.springframework.boot" % "spring-boot-starter-data-jpa" % vSpringBoot,



  //COMMON UTILS
  "ch.qos.logback" % "logback-classic" % "1.1.11",

  //UNIT-TESTING
  "org.scalactic" %% "scalactic" % vScalaTest, //http://www.scalactic.org/
  "org.scalatest" %% "scalatest" % vScalaTest % "test",
  "io.rest-assured" % "rest-assured" % "3.0.5" % "test",
  "org.springframework.boot" % "spring-boot-starter-test" % vSpringBoot % "test",
  "com.jayway.jsonpath" % "json-path-assert" % "2.3.0" % "test"
)