package de.allmaennitta.mindware.conceptmap

import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.Neo4jRepository
import java.lang.Long
import java.util

trait NodeRepository extends Neo4jRepository[Node, Long] {

  def findByName(name:String) : Node

  @Query("MATCH (n:Node) return n")
  def findAllNodes: util.Collection[Node]
}

