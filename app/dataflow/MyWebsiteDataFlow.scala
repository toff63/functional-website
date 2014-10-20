package dataflow

import components.MyWebsiteComponents
import scala.concurrent.Future
import service._
import scala.concurrent.ExecutionContext.Implicits.global

trait MyWebsiteDataFlow {this: MyWebsiteComponents =>
    def personAgeIn30Years(id:Int):Future[Int] = personDao.find(id).map(AgeBusinessRules.ageIn30Years _ compose Person.fromDao _)
    def dogAgeIn30Years(id:Int):Future[Int] = dogDao.find(id).map(AgeBusinessRules.ageIn30Years _ compose Dog.fromDao _)

}