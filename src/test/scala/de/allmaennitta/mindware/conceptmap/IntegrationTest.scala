package de.allmaennitta.mindware.conceptmap

import io.restassured.RestAssured
import io.restassured.RestAssured.when
import io.restassured.http.ContentType.JSON
import io.restassured.path.json.JsonPath
import org.scalatest.{FunSpec, Matchers}
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.core.env.Environment


//noinspection ScalaDeprecatedIdentifier
//@RunWith(classOf[SpringRunner])
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IntegrationTest extends FunSpec with Matchers with SpringTestContextManager {

  @Autowired var env : Environment = null
  @LocalServerPort var port = 0

  describe("Call of base path"){
    it("leads to redirect"){
      RestAssured.port = port
      val json =
        when.
          get("/").
        then.
          contentType(JSON).
          statusCode(200).
        extract.
          response.body.print

      JsonPath.from(json).getString("nodes[0].name") should be ("Amsel")
    }
  }
}

