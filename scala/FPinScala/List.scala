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

    //FP Exercise 3.5
    def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
        case Cons(x, xs) if f(x) => dropWhile(xs, f)
        case _ => l
    }

    //FP Exercise 3.6
    def init[A](l: List[A]): List[A] = l match {
        case Cons(x, Nil) => Nil
        case Cons(x, xs) => Cons(x, init(xs))
        case Nil => ???
    }
    
    //FoldRight
    def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B =
        as match {
            case Nil => z
            case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

    //length Exercise 3.9
    def length[A](as: List[A]): Int = {
        foldRight(as, 0)((_, y) => y + 1)
    }
}
