package service

import model.PersonDao
import model.DogDao
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import model.{Person => DaoPerson, Dog => DaoDog}

trait WithAge{val age:Int}

case class Person(name:String, age:Int) extends WithAge 
object Person{
  def fromDao(daoPerson:DaoPerson) = Person(daoPerson.name, daoPerson.age)
}

case class Dog(name:String, age:Int) extends WithAge
object Dog{
  def fromDao(daoDog:DaoDog) = Dog(daoDog.name, daoDog.age)
}

/**
 * Business rules should only depends on data. So those functions should receive the data they work on
 * and be pure. They TRANSFORM data. This part should be tested. As they are pure functions, you don't need
 * any mock or context to test them. They should be very easy to reuse!!
 */
object AgeBusinessRules{
  def ageIn30Years(person:WithAge):Int = person.age + 30
}