package controllers

import play.api._
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import components.MyWebsiteComponents
import dataflow.MyWebsiteDataFlow


object Application extends Controller with MyWebsiteComponents with MyWebsiteDataFlow{
 
  def index = Action.async{
    (for{
      johnAgeIn30Years <- personAgeIn30Years(1)
      bullAgeIn30Years <- dogAgeIn30Years(1)
    } yield  Ok(s"John will be $johnAgeIn30Years in 30 years and his dog will be $bullAgeIn30Years"))
    .recover{
      case _:Throwable => Ok("Currently have issues to retrieve data.")
    }
  }
}