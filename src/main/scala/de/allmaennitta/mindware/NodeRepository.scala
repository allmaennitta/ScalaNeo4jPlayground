package de.allmaennitta.mindware

import org.springframework.data.neo4j.repository.Neo4jRepository

trait NodeRepository extends Neo4jRepository[Node, String] {
  // derived finder
  def findByTitle(title:String): Node
}
