object method2 {

    def adjustPrices(prices: List[Double], policy: Double => Double): List[Double] =
        prices.map(policy)

    def discount(rate: Double): Double => Double = price =>
        BigDecimal(price * (1.0 - rate)).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble

    val promotionalStrategy: Double => Double = price =>
        BigDecimal(if (price > 100.0) price * 0.90 else price * 1.02)
            .setScale(2, BigDecimal.RoundingMode.HALF_UP)
            .toDouble

    def main(args: Array[String]): Unit = {
        val datos = List(10.0, 20.5, 150.0, 80.0)
        println(s"Descuento 15%:  ${adjustPrices(datos, discount(0.15))}")
        println(s"Estrategia Promocional: ${adjustPrices(datos, promotionalStrategy)}")
    }

}
