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

    //val wordList = Source.fromInputStream(getClass.getResourceAsStream("/public/text/words.txt"))
    //					 .getLines.toList
    					 
    val wordList = Source.fromFile("/usr/share/dict/words")
    		.getLines.toList.filter(word => (word.length > 5 && word.forall(Character.isLetter)))
    					 
    def index = Action { implicit request =>
    	Ok(views.html.hangman(readSession))
	}
    
    def start(level: Int) = Action { implicit request => 
    	val gameWord = wordList(rand.nextInt(wordList.size)).toUpperCase
    	val game = Hangman(gameWord, level)
    	println(write(game))
    	Ok(game.maskedWord).withSession(writeSession(write(game)))
    }
    
    private def writeSession(value: String)(implicit request: RequestHeader) = {
    	request.session + (sessionName -> value)
    }
    
    private def readSession(implicit request: RequestHeader): Option[Hangman] = {
	    request.session.get(sessionName).map{ hangman =>
	    	read[Hangman](hangman)
	    }
    }
    
    def guess(g: String) = Action { implicit request =>
	    readSession.map{ hangman =>
	      val lowGuess = g.toUpperCase()(0)
	      val misses = if(hangman.word.contains(lowGuess)) hangman.misses else hangman.misses + 1
	      val updatedGame = hangman.copy(guesses = hangman.guesses :+lowGuess, misses = misses)
	      val value = write(updatedGame)
	      Ok(updatedGame.maskedWord).withSession(writeSession(value))
	    }.getOrElse(BadRequest)
	}
}

