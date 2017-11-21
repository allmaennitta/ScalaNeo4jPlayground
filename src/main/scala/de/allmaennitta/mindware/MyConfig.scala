package de.allmaennitta.mindware

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
@EnableAutoConfiguration
class MyConfig(){
  @Bean
  def nodeController = new NodeController


}
