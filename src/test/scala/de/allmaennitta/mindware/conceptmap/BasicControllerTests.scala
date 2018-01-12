package de.allmaennitta.mindware.conceptmap

import java.util

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.{content, header, jsonPath, status}


@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
class BasicControllerTests extends FunSpec with Matchers with SpringTestContextManager {
  @Autowired private val mockMvc: MockMvc = null

  describe("A Request") {
    it("can be redirected") {
      this.mockMvc.
        perform(get("/").
          accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).
        andExpect(status.is3xxRedirection).
        andExpect(header.string("Location", "/node/all"))
    }
  }
}
