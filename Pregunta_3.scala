object method3 {

    def generadorIncrementadorConNombre(incremento: Int): Int => Int = {
        def incrementador(x: Int): Int = x + incremento
        incrementador
    }

    def main(args: Array[String]): Unit = {
        val generadorIncrementadorAnonimo: Int => (Int => Int) =
            (incremento: Int) =>
                (x: Int) =>
                x + incremento
        println(s"Incrementador sin nombre: ${generadorIncrementadorAnonimo(5)(10)}")
        println(s"Incrementador con nombre: ${generadorIncrementadorConNombre(5)(10)}")
    }

}