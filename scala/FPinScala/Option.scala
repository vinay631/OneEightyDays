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

    def filter(f: A => Boolean): Option[A] = this match {
        case Some(a) if f(a) => this
        case _ => None
    }

    def flatMap[B](f: A => Option[B]): Option[B] = this match {
        case None => None
        case Some(a) => f(a)
    } 

    def orElse[B >: A](ob: => Option[B]): Option[B] = this match {
        case None => ob
        case _ => this
    }

}

case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]

object Option {
    def mean(xs: Seq[Double]): Option[Double] = {
        if (xs.isEmpty) None
        Some(xs.sum/xs.length)
    }

    def variance(xs: Seq[Double]): Option[Double] = {
        mean(xs) flatMap(m => mean(xs.map(x => math.pow(x - m, 2))))
    }

    def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
        a flatMap (aa => b map (bb => f(aa, bb)))
    }
}
