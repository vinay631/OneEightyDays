package models

case class Hangman(word: String, level: Int = 0, guesses: List[Char] = Nil,
                   misses: Int = 0)
{
    
    def gameOver = won || (level < misses)

    def won = word forall guesses.contains

    def maskedWord = word map { c => if(guesses.contains(c)) c else '_' } mkString " "
}
