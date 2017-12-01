package de.allmaennitta.mindware.conceptmap
import java.lang.Long
import org.hibernate.validator.constraints.NotEmpty
import org.neo4j.ogm.annotation.{GraphId, NodeEntity, Relationship}

import scala.beans.BeanProperty


/**
  * See https://github.com/bijukunjummen/spring-boot-scala-web/
  */
@NodeEntity
class Node {
  def this(name:String){
    this();
    this.name = name
  }

  @GraphId
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


  override def toString = s"Node($id, $name)"

  def canEqual(other: Any): Boolean = other.isInstanceOf[Node]

  override def equals(other: Any): Boolean = other match {
    case that: Node =>
      (that canEqual this) &&
        name == that.name
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq()
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
