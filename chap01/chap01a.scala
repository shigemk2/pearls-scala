// minfree :: [Integer] -> Integer
// minfree xs = head([0..] \\ xs)

// (\\) :: Eq a => [a] -> [a] -> [a]
// us \\ vs = filter (notElem' vs) us

// notElem' :: Eq a => [a] -> a -> Bool
// notElem' a n = notElem n a

// main = do
//     print $ [1..9] \\ [3]
//     print $ minfree [08, 23, 09, 00, 12, 11, 01, 10, 13, 07, 41, 04, 14, 21, 05, 17, 03, 19, 02, 06]

def minfree(xs: List[Int]): Int = {
  // minfree xs = head([0..] \\ x)
  // Scalaでこういう書き方出来ない
  \\(Stream.from(0).take(100000).toList, xs).head
}

def \\(us: List[Int], vs: List[Int]): List[Int] = {
  us.filter(x => notElem(vs, x))
}

def notElem(a: List[Int], n: Int): Boolean = {
  ! a.contains(n)
}

println(minfree(List(8, 23, 9, 0, 12, 11, 1, 10, 13, 7, 41, 4, 14, 21, 5, 17, 3, 19, 2, 6)))
