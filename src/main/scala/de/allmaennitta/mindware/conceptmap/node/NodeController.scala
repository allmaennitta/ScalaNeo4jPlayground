package de.allmaennitta.mindware.conceptmap.node

import java.{lang, util}
import javax.servlet.http.HttpServletResponse

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

import scala.collection.JavaConverters
import scala.collection.immutable.HashMap

/**
  * See https://github.com/bijukunjummen/spring-boot-scala-web/
  */
@RestController
@RequestMapping(value = Array("/node"))
class NodeController {
  //noinspection VarCouldBeVal
  @Autowired var nodeRepository: NodeRepository = _

  @RequestMapping(value = Array("/all"), method = Array(RequestMethod.GET))
  def getAllNodes: util.Map[String, lang.Iterable[Node]] = {
    val nodes: util.Map[String, lang.Iterable[Node]] =
      JavaConverters.mapAsJavaMap(HashMap("nodes" -> nodeRepository.findAll()))
    nodes
  }
  @RequestMapping(value = Array("/byname/{name}"), method = Array(RequestMethod.GET))
  def getNode(@PathVariable("name")  name: String): Node = {
    nodeRepository.findByName(name)
  }

  @RequestMapping(value = Array("/create"), method = Array(RequestMethod.POST))
  def create(@RequestBody  input: Node): Node = {
    nodeRepository.save(input)
  }
}