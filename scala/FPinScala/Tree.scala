sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree{
    def size[A](tree:Tree[A]):Int = tree match {
        case Leaf(_) => 1
        case Branch(l, r) => 1 + size(l) + size(r)
    }

    def maximum(tree:Tree[Int]):Int = tree match {
        case Leaf(value:Int) => value
        case Branch(l, r) => maximum(l) max maximum(r)
    }

    def depth[A](tree: Tree[A]):Int = tree match {
        case Leaf(_) => 1
        case Branch(l, r) => 1 + depth(l) max depth(r)
    
    }

    def map[A, B](tree: Tree[A])(f: A => B):Tree[B] = tree match {
        case Leaf(a) => Leaf(f(a))
        case Branch(l, r) => Branch(map(l)(f), map(r)(f))
    }

    def fold[A, B](tree: Tree[A])(f: A => B) (g: (B, B) => B):B = tree match {
        case Leaf(a) => f(a)
        case Branch(l, r) => g(fold(l)(f)(g), fold(r)(f)(g))
    }

    def sizeWithFold[A](tree: Tree[A]):Int = {
        fold(tree)(a => 1)((l, r) => 1 + l + r)
    }

    def maxWithFold(tree: Tree[Int]):Int = {
        fold(tree)(a => a)(_ max _)
    }

    def depthWithFold[A](tree: Tree[A]):Int = {
        fold(tree)(_ => 1)(1 + _ max _)
    }
}
