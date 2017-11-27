package de.allmaennitta.mindware.conceptmap

import org.scalatest.{FunSpec, Matchers}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.{JacksonTester, JsonContent}
import com.jayway.jsonpath.matchers.JsonPathMatchers._
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers._


@JsonTest
class NodeJsonTests extends FunSpec with Matchers with SpringTestContextManager {

  @Autowired var json: JacksonTester[Node] = _

  describe("A Node") {
    it("can be serialized") {
      val details: Node = new Node()
      details.setId(0L)
      details.setName("Amsel")
      val jsonString: String = this.json.write(details).getJson

      println(jsonString)
      assertThat(jsonString, isJson)
      assertThat(jsonString, hasJsonPath("@.name", equalTo("Amsel")))
    }

    it("can be deserialized") {
      val content = "{\"id\": 99,\"name\":\"Ursula\"}"
      val expectedNode: Node = new Node
      expectedNode.setId(99L)
      expectedNode.setName("Ursula")
      assertThat(this.json.parse(content).getObject.name, equalTo("Ursula"))
    }
  }
}