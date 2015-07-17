import scala.{Option => _, Either => _, _}

sealed trait Option[+A] {
    
    def map[B](f: A => B): Option[B] = this match {
        case None => None
        case Some(a) => Some(f(a))
    }

    def getOrElse[B >: A](default: => B): B = this match {
        case None => default
        case Some(a) => a
    }


}

case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]
