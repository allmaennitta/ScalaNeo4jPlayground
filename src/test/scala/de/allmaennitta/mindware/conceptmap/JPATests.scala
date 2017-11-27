package de.allmaennitta.mindware.conceptmap

import org.scalatest.{FunSpec, Matchers}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JPATests  extends FunSpec with Matchers with SpringTestContextManager{
  @Autowired var repository: NodeRepository = _


  describe("Persisted Nodes") {
    it("can be retrieved from DB") {
        println(this.repository.findAllNodes.toString)
//        val nodes:List[Node] = this.repository.findAllNodes
//        val expectedNode = new Node
//        expectedNode.setName("Amsel")
//
////        nodes should (contain(expectedNode))
//        nodes.head should (be (expectedNode))
      }
    }
  }
