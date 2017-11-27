package de.allmaennitta.mindware.conceptmap


import java.{lang, util}
import javax.servlet.http.HttpServletResponse

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

import scala.collection.immutable.HashMap
import java.util.Map

import scala.collection.JavaConverters

/**
  * See https://github.com/bijukunjummen/spring-boot-scala-web/
  */
@RestController
class NodeController {
  //noinspection VarCouldBeVal
  @Autowired var nodeRepository: NodeRepository = _

  @RequestMapping(Array("/"))
  def handleRootRequest(response: HttpServletResponse): Unit = response.sendRedirect("/node/all")

  @RequestMapping(value = Array("/node/all"), method = Array(RequestMethod.GET))
  def getAllNodes: util.Map[String, lang.Iterable[Node]] = {
    val nodes: util.Map[String, lang.Iterable[Node]] =
      JavaConverters.mapAsJavaMap(HashMap("nodes" -> nodeRepository.findAll()))
    nodes
  }
}


