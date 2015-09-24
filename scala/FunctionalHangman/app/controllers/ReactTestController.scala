package controllers

import play.api._
import play.api.mvc._

object ReactTestController extends Controller {
	def index = Action { implicit request =>
    	Ok(views.html.reactindex("Welcome to Hangman!"))
	  //Ok(views.html.hangman("Welcome to Hangman!"))
	}
}