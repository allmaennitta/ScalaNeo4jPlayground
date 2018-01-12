package de.allmaennitta.mindware.conceptmap.node

import java.lang.Long
import java.util

import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.Neo4jRepository

trait NodeRepository extends Neo4jRepository[Node, Long] {

  def findByName(name:String) : Node

  @Query("MATCH (n:Node) return n")
  def findAllNodes: util.Collection[Node]
}

