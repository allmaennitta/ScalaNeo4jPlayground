package de.allmaennitta.mindware.conceptmap

import java.util
import java.util.List

import com.jayway.jsonpath.JsonPath
import de.allmaennitta.mindware.conceptmap.utils.SpringTestContextManager
import org.assertj.core.api.Assertions.assertThat
import org.scalatest.{FunSpec, Matchers}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.{get, post}
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.{content, header,
  jsonPath, status}

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
class NodeControllerTests extends FunSpec with Matchers with SpringTestContextManager {
  @Autowired private val mockMvc: MockMvc = null
  @Autowired var rep: NodeRepository = _

  describe("A Request") {
    it("can be redirected") {
      this.mockMvc.
        perform(get("/").
          accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).
        andExpect(status.is3xxRedirection).
        andExpect(header.string("Location", "/node/all"))
    }

    it("can retrieve all nodes") {
      val json: String = this.mockMvc.
        perform(get("/node/all").
          accept(MediaType.APPLICATION_JSON_UTF8)).
          andExpect(status.isOk).
          andExpect(content.contentType("application/json;charset=UTF-8")).
        andReturn.getResponse.getContentAsString

      val result: util.List[String] = JsonPath.parse(json).read("$.nodes[*].name")
      assertThat(result.toArray()).contains("Cuckoo", "Eukaryota")
    }

    it("can retrieve a node by name") {
      this.mockMvc.
        perform(get("/node/Opisthokonta").
          accept(MediaType.APPLICATION_JSON_UTF8)).
          andExpect(status.isOk).
          andExpect(content.contentType("application/json;charset=UTF-8")).
          andExpect(jsonPath("@.name").value("Opisthokonta"))
    }

    it("can provoke creation of a node") {
      this.mockMvc.
        perform(
          post("/node/create").
          content("{\"name\":\"ControllerTestKnoten\"}").
          contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status.isOk)
        .andExpect(content.contentType("application/json;charset=UTF-8"))

      val node: Node = rep.findByName("ControllerTestKnoten")
      rep.delete(node.getId)
    }
  }
}
