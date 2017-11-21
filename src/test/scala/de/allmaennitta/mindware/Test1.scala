package de.allmaennitta.mindware
import org.scalatest.{FunSpec, Matchers}

class Test1 extends FunSpec with Matchers {
  /**
    */


  describe("the type of a collection"){
    it("is determined by inference "){
      List(1, 2.0, 33D, 400L).head.getClass.getCanonicalName should be ("double")
    }
  }
}