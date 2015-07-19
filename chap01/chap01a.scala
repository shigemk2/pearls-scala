def minfree(xs: List[Int]): Int = {
  // minfree xs = head([0..] \\ x)
  // Scalaでこういう書き方出来ない
  // \\(Stream.from(0).take(100000).toList, xs).head
  Stream.from(0).filter(x => ! xs.contains(x)).head
}

def \\(us: List[Int], vs: List[Int]): List[Int] = {
  us.filter(x => notElem(vs, x))
}

def notElem(a: List[Int], n: Int): Boolean = {
  ! a.contains(n)
}

println(minfree(List(8, 23, 9, 0, 12, 11, 1, 10, 13, 7, 41, 4, 14, 21, 5, 17, 3, 19, 2, 6)))
