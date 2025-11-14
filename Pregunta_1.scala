def myMethod(datos: List[Double]): Double = {
  val logs = datos.map(Math.log)

  val promedioLog = logs.sum / logs.size

  val sumaCuadrados = logs
    .map(l => math.pow(l - promedioLog, 2))
    .sum

  math.sqrt(sumaCuadrados / logs.size)
}
