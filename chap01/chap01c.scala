// import Data.Array

// countlist :: [Integer] -> Array Integer Integer

// countlist xs = accumArray (+) 0 (0, n) (zip xs (repeat 1))
//     where n = toInteger (length xs)

// main = do
//     print $ countlist [1,2,3,4,5]
// http://stackoverflow.com/questions/14781919/haskell-accumarray-equivalent-in-scala
def accumArray[A <% Ordered[A], B, C](f: (B, C) => B,
  base: B,
  bounds: (A, A),
  xs: Seq[(A, C)]): Vector[(A, B)] = {
  @scala.annotation.tailrec
  def accum(ys: Seq[(A, C)], zs: Vector[(A, B)]): Vector[(A, B)] =
    (ys, zs) match {
      case(Seq(), _)           => zs
      case(((a,c)) +: rs, Vector())  => accum(rs, Vector((a, f(base,c))))
      case(((a1,c)) +: rs, vs :+ ((a2,b))) =>
        if(a1 == a2) accum(rs, vs :+ (a2, f(b,c)))
        else accum(rs, zs :+ (a1, f(base,c)))
    }

  val (min, max) = bounds
  val ys = xs.filter{case(x, _) => x >= min && x <= max}.sortBy(_._1)
  accum(ys, Vector())
}

def countlist(xs: List[Int]): Vector[(Int, Int)] = {
  val n = xs.length
  accumArray(((x: Int, y: Int) => x + y), 0, (0,n), xs.zip(Stream.iterate(1){x => x}))
}

println(List(1,2,3,4,5).zip(Stream.iterate(1){x => x}))
println(accumArray(((x: Int, y: Int) => x + y), 0, (0,5), List((1,1), (2,1), (3,1), (4,1), (5,1))))
println(countlist(List(1,2,3,4,5)))
