package de.allmaennitta.mindware.conceptmap

import org.springframework.hateoas.mvc.ControllerLinkBuilder._

import java.{lang, util}
import javax.servlet.http.HttpServletResponse

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._
import org.springframework.http.HttpEntity
import scala.collection.JavaConverters
import scala.collection.immutable.HashMap
import org.springframework.hateoas.mvc.ControllerLinkBuilder._


/**
  * See https://github.com/bijukunjummen/spring-boot-scala-web/
  */
@RestController
class BaseController {
  @RequestMapping(Array("/"))
  def handleRootRequest(): HttpEntity[Hateoas] = {
    val hateoas : Hateoas = new Hateoas("Test des Root-Requests")
    hateoas.add(
      linkTo(methodOn(classOf[BaseController]).handleRootRequest())
        .withSelfRel())
  }
}