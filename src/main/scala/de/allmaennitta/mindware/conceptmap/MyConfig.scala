package de.allmaennitta.mindware.conceptmap

import java.nio.charset.Charset

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.neo4j.ogm.config.Configuration
import org.neo4j.ogm.session.SessionFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

@SpringBootApplication
class MyConfig(){
  @Bean def getSessionFactory = new SessionFactory(configuration, "de.allmaennitta.mindware.conceptmap")

  @Bean
  @throws[Exception]
  def transactionManager = new Neo4jTransactionManager(getSessionFactory)

  @Bean def configuration: Configuration = {
    val config = new Configuration
    config.driverConfiguration.setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
      .setURI("http://ingo:ingo@localhost:7474")
    config
  }
}
