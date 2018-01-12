package de.allmaennitta.mindware.conceptmap

import com.jayway.jsonpath.JsonPath
import de.allmaennitta.mindware.conceptmap.node.{Node, NodeRepository}
import de.allmaennitta.mindware.conceptmap.utils.SpringTestContextManager
import io.restassured.RestAssured
import io.restassured.RestAssured.{given, when}
import io.restassured.http.ContentType.JSON
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.scalatest.{BeforeAndAfter, FunSpec, Matchers}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.context.{SpringBootTest, TestConfiguration}


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestConfiguration
class BasicRestIntegrationTests extends FunSpec with Matchers with SpringTestContextManager with
  BeforeAndAfter {
  @LocalServerPort var port = 0

  @Autowired var rep: NodeRepository = _

  before {
    RestAssured.port = port
  }

  describe("A request") {
    it("with base path leads to redirect") {
      given.redirects.follow(false).
        when.get("/").
        then.
        statusCode(302).
        header("location", s"http://localhost:$port/node/all")
    }

    it("with special chars is automatically url-encoded and url-unencoded") {
      val nodeSpecialChars =
        given.
          contentType("application/json").
          body(new Node("Buße in " + "Höhe von 100$ in €")).
          when.
          post("/node/create").
          then.
          contentType(JSON).statusCode(200).
          extract.response.body.as(classOf[Node])

      assertThat(nodeSpecialChars.getName).isEqualTo("Buße in Höhe von 100$ in €")

      val resultNode =
        when.
          get("/node/Buße in Höhe von 100$ in €")
          .then.
          contentType(JSON).
          statusCode(200).
          extract.response.body.as(classOf[Node])

      assertThat(resultNode.getName).isEqualTo("Buße in Höhe von 100$ in €")
      rep.deleteAll()
    }
  }

  describe("A thrown error") {
    it("is catched if of type Nullpointer") {
      val response = when.post("/error/nullpointer").then.extract.response
      assertThat(response.body.asString).contains("Server Fault")
      assertThat(response.statusCode).isEqualTo(500)
    }
    it("is catched if of type IllegalArgument") {
      val response = when.post("/error/illegal_argument").then.extract.response
      assertThat(response.body.asString).contains("API Fault")
      assertThat(response.statusCode).isEqualTo(400)
    }

    it("is catched if of type UnsupportedOperation") {
      val response = when.post("/error/unsupported_operation").then.extract.response
      assertThat(response.body.asString).contains("Unknown Fault")
      assertThat(response.statusCode).isEqualTo(501)
    }
  }
}

