def generadorIncrementador(incremento: Int): Int => Int =
  (x: Int) => x + incremento
