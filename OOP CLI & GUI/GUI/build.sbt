name := """Backend"""
organization := "com.nazhim"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.3"

libraryDependencies += guice

// References:- Dilums Lecture 03 one running backend and frontend using a single command "sbt run"