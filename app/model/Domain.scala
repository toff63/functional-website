package model

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class Person(id:Int, name:String, age:Int)

class PersonDao extends Dao[Person]{
  def find(id:Int):Future[Person] = Future(Person(1, "John", 20))
}

case class Dog(id:Int, name:String, age:Int)

trait Dao[T] {
  def find(id:Int):Future[T]
}

class DogDao extends Dao[Dog]{
  def find(id:Int):Future[Dog] = Future(Dog(1, "Bull", 20))
}