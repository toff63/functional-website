package model

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class Person(id:Int, name:String, age:Int)
class PersonDao{
  def find(id:Int):Future[Person] = Future(Person(1, "John", 20))
}

case class Dog(id:Int, name:String, age:Int)
class DogDao{
  def find(id:Int):Future[Dog] = Future(Dog(1, "Bull", 20))
}