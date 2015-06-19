object Fibo extends App {
    def fib(n: Int):Int = 
      n match {
        case 1 => 0
        case 2 => 1
        case y:Int => fib(y - 1) + fib(y - 2)
      }

      println(fib(10))
}


