package service

import org.specs2.mutable.Specification
import service._

class DomainServiceTest extends Specification {

  "AgeBusinessRules" should {
    "return 50 to a 20 years old person" in {
      AgeBusinessRules.ageIn30Years(Person("John", 20)) must equalTo(50)
    }
    
  }
}

