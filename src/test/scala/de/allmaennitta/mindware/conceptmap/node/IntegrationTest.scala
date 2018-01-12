package de.allmaennitta.mindware.conceptmap.node

import com.jayway.jsonpath.JsonPath
import de.allmaennitta.mindware.conceptmap.utils.SpringTestContextManager
import io.restassured.RestAssured
import io.restassured.RestAssured.{given, when}
import io.restassured.http.ContentType.JSON
import org.assertj.core.api.Assertions.assertThat
import org.scalatest.{BeforeAndAfter, FunSpec, Matchers}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.context.{SpringBootTest, TestConfiguration}


//noinspection ScalaDeprecatedIdentifier
//@RunWith(classOf[SpringRunner])
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestConfiguration
class IntegrationTest extends FunSpec with Matchers with SpringTestContextManager with
  BeforeAndAfter {
  @LocalServerPort var port = 0

  @Autowired var rep: NodeRepository = _

  before{
    RestAssured.port = port
  }

  describe("A request"){
    it("shows all nodes"){
      val json =
        when.
          get("/node/all").
        then.
          contentType(JSON).
          statusCode(200).
        extract.
          response.body.print

      val result: java.util.List[String] = JsonPath.parse(json).read("$.nodes[*].name")
      assertThat(result.toArray).contains("Filozoa", "Holozoa")
    }

    it("reads by name"){
      val jsonNode =
        when.
          get("/node/byname/Chordata").
          then.
            contentType(JSON).
            statusCode(200)
        .extract.
          response.body.as(classOf[Node])

      assertThat(jsonNode.getName).isEqualTo("Chordata")
    }
    it("creates nodes"){
      val nodeCreated =
        given.
          contentType("application/json").
          body(new Node("Testknoten")).
        when.
          post("/node/create").
        then.
          contentType(JSON).
          statusCode(200).
        extract.
          response.body.as(classOf[Node])

      val id = nodeCreated.getId
      assertThat(id > 0L)
      assertThat(nodeCreated.getName).isEqualTo("Testknoten")

      val nodeUnderTest: Node = rep.findOne(nodeCreated.getId)
      println(nodeUnderTest + "=============" + nodeCreated)
      assertThat(nodeUnderTest).isEqualTo(nodeCreated)
      rep.delete(nodeCreated.getId)
    }
  }

}

