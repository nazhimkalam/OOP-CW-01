/**
 * Frontend build commands.
 * Change these if you are using some other package manager. i.e: Yarn
 */
object FrontendCommands {
  val dependencyInstall: String = "npm install"
  val test: String = "npm run test:ci"
  val serve: String = "npm run start"
  val build: String = "npm run build:prod"
}

// References:- Dilums Lecture 03 one running backend and frontend using a single command "sbt run"