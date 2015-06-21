object Sorted extends App {
    def isSorted[A](as:Array[A], ordered:(A, A) => Boolean):Boolean = {
        if(as.length == 1) true
        else ordered(as(0), as(1)) && isSorted(as.slice(1, as.length), ordered)
    }

    println(isSorted(Array(2, 5, 4), (x:Int, y:Int) => x <= y))
}
