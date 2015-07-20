package models

case class Hangman(word: String, guesses: List[Char] = Nil,
                   misses: Int = 0, maxMisses: Int = 0)
{
    
    def gameOver = won || (guesses.size < misses)

    def won = word forall guesses.contains

    def maskedWord = word map { c => if(guesses.contains(c)) c else '_' } mkString " "
}
