object method {

    def myMethod(datos: List[Double]): Double = datos match {
        case xs =>
            val logs = xs.map(math.log)
            val n = logs.size
            val promedy = logs.sum / n
            math.sqrt(logs.map { x => val d = x - promedy; d * d }.sum / n)
    }

    def main(args: Array[String]): Unit = {
        val datos = List(1.0, 2.0, 3.0, 4.0, 5.0)
        println(myMethod(datos))
    }

}
