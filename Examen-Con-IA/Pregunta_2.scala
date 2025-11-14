object AjustePrecios {

  // Función general: recibe la lista de precios y la política de ajuste
  def ajustarPrecios(
      precios: List[Double],
      politica: Double => Double
  ): List[Double] =
    precios.map(politica)

  // Políticas de ejemplo -----------------------------

  // Agregar un impuesto (IVA)
  val agregarImpuesto: Double => Double = precio =>
    precio * 1.21      // +21%

  // Aplicar un descuento fijo en porcentaje
  def descuento(pct: Double): Double => Double = precio =>
    precio * (1 - pct)

  // Ajuste por temporada (p. ej. inflación o demanda)
  def ajustePorFactor(factor: Double): Double => Double = precio =>
    precio * factor

  // Política más compleja: precio mínimo garantizado
  def precioMinimo(min: Double): Double => Double = precio =>
    math.max(precio, min)

  // Ejemplo de uso ------------------------------------
  def main(args: Array[String]): Unit = {
    val precios = List(100.0, 200.0, 300.0)

    println("Con impuesto:")
    println(ajustarPrecios(precios, agregarImpuesto))

    println("Con 10% de descuento:")
    println(ajustarPrecios(precios, descuento(0.10)))

    println("Ajuste por temporada x1.05:")
    println(ajustarPrecios(precios, ajustePorFactor(1.05)))

    println("Aplicar precio mínimo de 250:")
    println(ajustarPrecios(precios, precioMinimo(250)))
  }
}
