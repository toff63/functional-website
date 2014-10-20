package dataflow

import components.MyWebsiteComponents
import scala.concurrent.Future
import service._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Try, Success, Failure}

trait MyWebsiteDataFlow { this: MyWebsiteComponents =>
  def personAgeIn(id: Int, yearsToAdd:Int): Future[Int] = {
    val ageInYears = AgeBusinessRules.ageIn(yearsToAdd, _:Person) 
    val age = ageInYears compose Person.fromDao _

    for {
      validatedId <- tryToFuture(Validation.validate(id))
      person <- personDao.find(validatedId)
    } yield age(person)
    
  }
    
  def dogAgeIn30Years(id: Int): Future[Int] = dogDao.find(id).map(AgeBusinessRules.ageIn30Years _ compose Dog.fromDao _)

  def tryToFuture[T](t: Try[T]): Future[T] = {
    t match {
      case Success(s) => Future.successful(s)
      case Failure(ex) => Future.failed(ex)
    }
  }
}