package de.allmaennitta.mindware.conceptmap

import java.util
import java.util.List

import de.allmaennitta.mindware.conceptmap.utils.SpringTestContextManager
import org.assertj.core.api.Assertions.assertThat
import org.scalatest.{FunSpec, Matchers}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JPATests extends FunSpec with Matchers with SpringTestContextManager {
  @Autowired var repository: NodeRepository = _


  describe("Persisted Nodes") {
    it("can all be retrieved") {
      val nodes: util.Collection[Node] = repository.findAllNodes
      assertThat(nodes.size).isGreaterThan(5)
    }

    it("can be retrieved by name") {
      val node: Node = repository.findByName("Animalia")
      assertThat(node.getId).isGreaterThan(0L)
      assertThat(node.getName).isEqualTo("Animalia")
    }

    it("can be created") {
      val createdNode: Node = repository.save(new Node("Magpie"))
      assertThat(repository.exists(createdNode.getId)).isTrue
      val foundNode: Node = repository.findByName("Magpie")
      assertThat(createdNode).isEqualTo(foundNode)
      foundNode.setName("Lapwing")
      val changedNode: Node = foundNode
      repository.save(changedNode)
      val refoundChangedNode: Node = repository.findByName("Lapwing")
      assertThat(refoundChangedNode.getId).isEqualTo(createdNode.getId)
      repository.delete(refoundChangedNode)
      assertThat(repository.exists(createdNode.getId)).isFalse
    }
  }
}
