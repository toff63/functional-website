package controllers

import play.api._
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import components.MyWebsiteComponents
import dataflow.MyWebsiteDataFlow

object Application extends Controller with MyWebsiteComponents with MyWebsiteDataFlow {

  def index = personAndDogAge(1, 30)

  def personAndDogAge(id: Int, yearsToAdd:Int) = Action.async {
    (for {
      johnAgeIn30Years <- personAgeIn(id, yearsToAdd)
      bullAgeIn30Years <- dogAgeIn30Years(id)
    } yield Ok(s"John will be $johnAgeIn30Years in $yearsToAdd years and his dog will be $bullAgeIn30Years in 30 years"))
      .recover {
        case _: Throwable => Ok("Currently have issues to retrieve data.")
      }
  }
}