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


    //FoldLeft
    def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = {
        @annotation.tailrec
        def fold(as: List[A], z: B): B = as match{
            case Nil => z
            case Cons(x, xs) => fold(xs, f(z, x))
        }
        fold(as, z)
    }

    //Sum 3.11
    def sum(as: List[Int]) = {
        foldLeft(as, 0)(_+_)
    }

    //Product 3.11
    def product(as: List[Int]) = {
        foldLeft(as, 1)(_*_) 
    }

    //Length 3.11
    def length2[A](as: List[A]):Int = {
        foldLeft(as, 0)((y, _) => y + 1)
    }

    //Reverse 3.12 - Not sure if it is right
    def reverse[A](as: List[A]):List[A] = {
        foldLeft(as, List[A]())((y, x) => Cons(x, y))
    }

    //3.14
    def append[A](as: List[A], elem: A):List[A] = {
        foldRight(as, List[A](elem))((x, y) => Cons(x, y))
    }

    //3.16
    def add(l: List[Int]):List[Int] = l match {
        case Nil => Nil
        case Cons(x, xs) => Cons(x+1, add(xs))
    }

    //3.17
    def stringConverter(l: List[Double]):List[String] = l match {
        case Nil => Nil
        case Cons(x, xs) => Cons(x.toString, stringConverter(xs))
    }

    //3.18
    def map[A, B](l: List[A])(f: A => B):List[B] = l match {
        case Nil => Nil
        case Cons(x, xs) => Cons(f(x), map(xs)(f))
    }

    //3.19
    def filter[A](l:List[A])(f: A => Boolean):List[A] = l match {
        case Nil => Nil
        case Cons(x, xs) if f(x) => Cons(x, filter(xs)(f))
        case _ => filter(tail(l))(f)
    }

    def concatLists[A](l1:List[A], l2:List[A]):List[A] = l1 match {
        case Nil => l2
        case Cons(h, t) => Cons(h, concatLists(t, l2))
    
    }

    def flattenList[A](l: List[List[A]]):List[A] = {
        foldRight(l, Nil:List[A])(concatLists)
    }

    //3.20
    def flatMap[A, B](as: List[A])(f: A => List[B]): List[B] = {
        flattenList(map(as)(f))
    }

    //3.21
    def filterFlatMap[A](l: List[A])(f: A => Boolean):List[A] = {
        flatMap(l)(a => if (f(a)) List(a) else Nil)
    }

    def listAdd(a: List[Int], b: List[Int]):List[Int] = (a, b) match {
        case (Nil, Nil) => Nil
        case (Cons(h, t), Nil) => Cons(h, t)
        case (Nil, Cons(h, t)) => Cons(h, t)
        case (Cons(h1, t1), Cons(h2, t2)) => Cons(h1 + h2, listAdd(t1, t2))
    
    }

    def zipWith[A, B, C](a: List[A], b: List[B])(f: (A, B) => C):List[C] = (a, b) match {
        case (_, Nil) => Nil
        case (Nil, _) => Nil
        case (Cons(h1, t1), Cons(h2, t2)) => Cons(f(h1, h2), zipWith(t1, t2)(f))
    }
        

}
