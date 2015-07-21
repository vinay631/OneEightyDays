package controllers

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
import play.api.mvc._
import scala.concurrent.Future
import models.Hangman

import upickle.default._

import scala.io.Source
import scala.util.Random

object HangmanController extends Controller {

    val rand = new Random()
    
    val sessionName = "Hangman"

    val wordList = Source.fromInputStream(getClass.getResourceAsStream("/public/text/words.txt"))
    					 .getLines.toList
    
    def start(level: Int) = Action { implicit request => 
    	val gameWord = wordList(rand.nextInt(wordList.size)).toUpperCase
    	val game = Hangman(gameWord, level)
    	Ok(game.maskedWord).withSession(sessionName -> write(game))
    }


}

