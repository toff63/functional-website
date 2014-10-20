package dataflow

import org.specs2.mutable.Specification
import components.MyWebsiteComponents
import model._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.Duration

class PersonFailingDao extends Dao[Person]  {
  def find(id:Int):Future[Person] = Future(throw new RuntimeException("Connection lost"))
}
trait FailingDao extends MyWebsiteComponents {
  override val personDao: Dao[Person] = new PersonFailingDao
  override val dogDao: Dao[Dog] = new DogDao
}

class MyDaoFailingFlow extends MyWebsiteDataFlow with FailingDao
class MyWebsiteDataFlowTest extends Specification {

  "MyWebsiteDataFlowTest" should {
    "Return an error if connection to databse is lost" in {
      Await.result((new MyDaoFailingFlow).personAgeIn(1, 30).recover{case _:RuntimeException => 0}, Duration.Inf) == 0
    }
  }
}