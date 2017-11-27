package de.allmaennitta.mindware.conceptmap
import java.lang.Long
import org.hibernate.validator.constraints.NotEmpty
import org.neo4j.ogm.annotation.{GraphId, NodeEntity, Relationship}
import org.springframework.data.annotation.Id
import javax.persistence.GeneratedValue

import scala.beans.BeanProperty


/**
  * See https://github.com/bijukunjummen/spring-boot-scala-web/
  */
@NodeEntity
class Node {

  @Id
  @GraphId
  @GeneratedValue
  @BeanProperty
  var id: Long = _

  @BeanProperty
  @NotEmpty
  var name: String = _

  //noinspection ScalaUnusedSymbol
  @Relationship(`type` = "IS_A", direction = Relationship.OUTGOING)
  private var node = null
  //noinspection ScalaUnusedSymbol
  @Relationship(`type` = "HAS_SUBTYPES", direction = Relationship.OUTGOING)
  private var subtypes = null


}
