package service

import model.PersonDao
import model.DogDao
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Service class are only responsible to get data from controller and persistence layer 
 * and pass them to services objects containing business rules. They contain no logic at all, 
 * they only move data around without any transformation. Having no logic, they don't need to be 
 * tested.
 */
class PersonService{
  val dao:PersonDao = new PersonDao
  def ageIn30Years(id:Int):Future[Int] = dao.find(id).map(person => AgeBusinessRules.ageIn30Years(person.age))
}

class DogService{
  val dao:DogDao = new DogDao
  def ageIn30Years(id:Int):Future[Int] = dao.find(id).map(dog => AgeBusinessRules.ageIn30Years(dog.age))
}


/**
 * Business rules should only depends on data. So those functions should receive the data they work on
 * and be pure. They TRANSFORM data. This part should be tested. As they are pure functions, you don't need
 * any mock or context to test them. They should be very easy to reuse!!
 */
object AgeBusinessRules{
  def ageIn30Years(age:Int):Int = age + 30
}