package controllers

import play.api._
import play.api.mvc._
import service._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Application extends Controller {

  val personService:PersonService = new PersonService
  val dogService:DogService = new DogService
  
  def index = Action.async {
    johnAndBullAgesIn30Years.map{ages  => 
      val (johnAge, bullAge) = ages
      Ok(s"John will be $johnAge in 30 years and his dog will be $bullAge")
    }
  }

  def johnAndBullAgesIn30Years = for{
      johnAgeIn30Years <- personService.ageIn30Years(1)
      bullAgeIn30Years <- dogService.ageIn30Years(1)
    }yield (johnAgeIn30Years, bullAgeIn30Years)
}


