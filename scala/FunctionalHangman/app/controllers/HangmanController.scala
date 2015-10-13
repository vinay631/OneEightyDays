package controllers

import scala.io.Source
import scala.util.Random

import models.Hangman
import play.api.data.Form
import play.api.data.Forms.nonEmptyText
import play.api.data.Forms.single
import play.api.libs.json._
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.mvc.RequestHeader
import upickle.default.macroR
import upickle.default.macroW
import upickle.default.read
import upickle.default.write

object HangmanController extends Controller {

    val rand = new Random()
    
    val sessionName = "Hangman"
    					 
    //Need to figure out where to put this file
    val wordList = Source.fromFile("/usr/share/dict/words")
    		.getLines.toList.filter(word => (word.length > 5 && word.forall(Character.isLetter)))
    					 
    def index = Action { implicit request =>
    	Ok(views.html.hangman("Welcome to Hangman!"))
	}
    
    def start = Action { implicit request => 
    	val gameWord = wordList(rand.nextInt(wordList.size)).toUpperCase
    	println(gameWord)
    	val game = Hangman(gameWord)
    	val returnJson = getReturnJson(game, "Starting...")
		Ok(returnJson).withSession(writeSession(write(game)))
    }
    
    private def writeSession(value: String)(implicit request: RequestHeader) = {
    	request.session + (sessionName -> value)
    }
    
    private def readSession(implicit request: RequestHeader): Option[Hangman] = {
	    request.session.get(sessionName).map{ hangman =>
	    	read[Hangman](hangman)
	    }
    }
    
    val guessForm = Form(
    	single(
    		"guess" -> nonEmptyText 
    	)
    )
    
    def guessSubmit = Action { implicit request =>
      readSession.map{ hangman =>
    	val lowGuess =  guessForm.bindFromRequest.get.toUpperCase()(0)
    	val misses = if(hangman.word.contains(lowGuess)) hangman.misses else hangman.misses + 1
	      val updatedGame = hangman.copy(guesses = hangman.guesses :+lowGuess, misses = misses)
	      if (updatedGame.won) {
	        val returnJson = getReturnJson(updatedGame, "You Won!")
	        Ok(returnJson).withNewSession
	      } else {
	        if (updatedGame.gameOver) {
	          val returnJson = getReturnJson(updatedGame, "You Lost!")
	          println(returnJson)
	          Ok(returnJson).withNewSession
	        } else {
	          val value = write(updatedGame)
	          
		      val returnJson = getReturnJson(updatedGame, "")
	          Ok(returnJson).withSession(writeSession(value))
	        }
	      }
	    }.getOrElse(BadRequest)	
    }
    
    def guess(g: String) = Action { implicit request =>
	    readSession.map{ hangman =>
	      val lowGuess = g.toUpperCase()(0)
	      val misses = if(hangman.word.contains(lowGuess)) hangman.misses else hangman.misses + 1
	      val updatedGame = hangman.copy(guesses = hangman.guesses :+lowGuess, misses = misses)
	      if (updatedGame.won) {
	        val returnJson = getReturnJson(updatedGame, "You Won!",isWon=true, isLost=false)
	        Ok(returnJson).withNewSession
	      } else {
	        if (updatedGame.gameOver) {
	          val returnJson = getReturnJson(updatedGame, "You Lost!",isLost=true, isWon=false)
	          Ok(returnJson).withNewSession
	        } else {
	          val value = write(updatedGame)
	          val returnJson = getReturnJson(updatedGame, "")
	          Ok(returnJson).withSession(writeSession(value))
	        }
	      }
	    }.getOrElse(BadRequest)
	}
    
    def giveUp() = Action { implicit request =>
    	readSession.map{ hangman =>
    	  val returnJson = getReturnJson(hangman, "Here is the word : " + hangman.word)
    	  Ok(returnJson) withNewSession
    	}.getOrElse(BadRequest)
    
    }
    
    def getReturnJson(game: Hangman, message: String, isWon: Boolean=false, isLost: Boolean=false) = {
      val returnJson = Json.obj(
		    "word" -> game.maskedWord,
		    "misses" -> game.misses ,
		    "message" -> message,
		    "won" -> isWon,
		    "lost" -> isLost
		)
		
	  returnJson
    }
}

