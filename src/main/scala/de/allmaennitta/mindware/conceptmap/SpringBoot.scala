package de.allmaennitta.mindware

import de.allmaennitta.mindware.conceptmap.MyConfig
import org.springframework.boot.SpringApplication

//TODO I'd like to know why the app doesn't run (java.lang.IncompatibleClassChangeError)
// as soon as I set the correct package (de.allmaennitta.mindware.conceptmap)
// That's strange.
object SpringBoot extends App {
  SpringApplication.run(classOf[MyConfig])
}

