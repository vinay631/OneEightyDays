sealed trait List[+A]

case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

    def apply[A](as: A*): List[A] = 
        if (as.isEmpty) Nil
        else Cons(as.head, apply(as.tail: _*))

    //FP Exercise 3.2
    def tail[A](as: List[A]): List[A] = as match {
        case Nil => Nil
        case Cons(x, xs) => xs
    }

    //FP Exercist 3.3
    def setHead[A](a: A, as: List[A]): List[A] = {
        return Cons(a, as) 
    }

    //FP Exercise 3.4
    def drop[A](l: List[A], n: Int): List[A] = n match {
        case 0 => l
        case n if n < 0 => l
        case _ => drop(tail(l), n-1)
    }
}
