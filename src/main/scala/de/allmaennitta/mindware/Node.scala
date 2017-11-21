package de.allmaennitta.mindware

import com.fasterxml.jackson.databind.BeanProperty
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import org.springframework.data.annotation.Id

@NodeEntity class Node {
  @Id
  val name = null
  @Relationship(`type` = "IS", direction = Relationship.OUTGOING) val is_a = null
  @Relationship(`type` = "EXAMPLE", direction = Relationship.INCOMING) val could_be_a = null
}
