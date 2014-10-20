package components

import model._

trait MyWebsiteComponents {
  val personDao: Dao[Person] = new PersonDao
  val dogDao: Dao[Dog] = new DogDao
}