# README

# Pregunta 1

## Prompt original
Ayúdame a transformar el siguiente método de Java que esta desarrollado de forma imperativa, quiero que lo transformes a una versión funcional y hazlo en el lenguaje de scala(.scala) Este es el metodo:

double myMethod(List<Double> datos) { 
    List<Double> logs = new ArrayList<>(); 
    for (int i = 0; i < datos.size(); i++) { 
        logs.add(Math.log(datos.get(i))); 
    } 
    double sumaLogs = 0.0; 
    for (int i = 0; i < logs.size(); i++) { 
        sumaLogs += logs.get(i); 
    } 
    double promedioLog = sumaLogs / logs.size(); 
    double sumaCuadrados = 0.0; 
    for (int i = 0; i < logs.size(); i++) { 
        double diferencia = logs.get(i) - promedioLog; 
        sumaCuadrados += diferencia * diferencia; 
    } 
    return Math.sqrt(sumaCuadrados / logs.size()); 
}

## Solución propuesta (Scala, estilo funcional)
```scala
def myMethod(datos: List[Double]): Double = {
    if (datos.isEmpty) Double.NaN
    else {
        val logs = datos.map(math.log)
        val mean = logs.sum / logs.size
        val variance = logs.map(x => math.pow(x - mean, 2)).sum / logs.size
        math.sqrt(variance)
    }
}
```

## Ejemplo de uso
- Entrada: List(1.0, 2.0, 3.0)
- Llamada: myMethod(List(1.0,2.0,3.0))
- Salida: Double con la desviación estándar de los logaritmos naturales de los valores.

# Pregunta 2

## Prompt original
Ayudame a generar un codigo para poder resolver los siguientes requerimientos quiero que el codigo sea hecho en scala(.scala) quiero que el codigo sea funcional y entendible, estos son los requerimientos( Ajuste de precios con políticas variables En una tienda digital se trabaja con listas de precios que necesitan ser ajustadas antes de ser mostradas al usuario. De acuerdo con el país, la temporada o las promociones vigentes, el ajuste puede consistir en agregar un impuesto, aplicar un descuento o modificar el precio según cierta estrategia comercial. Se requiere elaborar una solución donde exista una única función capaz de recibir la lista de precios y, además, la función que representa la regla de ajuste que se quiera aplicar en ese momento. Esta función deberá retornar una nueva lista de precios ya modificados de acuerdo con la política específica que se haya suministrado.)

## Solución propuesta (Scala, estilo funcional)
```scala
object PriceAdjuster {
  // Tipo alias para la política de ajuste: recibe un precio y devuelve el precio ajustado
  type Policy = Double => Double

  // Función genérica: recibe la lista de precios y la política, devuelve nueva lista ajustada
  def adjustPrices(prices: List[Double], policy: Policy): List[Double] =
    prices.map(policy)

  // Políticas reutilizables (funciones puras)
  def addTax(rate: Double): Policy = price => price * (1.0 + rate)        // rate = 0.19 para 19%
  def discount(percent: Double): Policy = price => price * (1.0 - percent) // percent = 0.10 para 10%
  def fixedIncrease(amount: Double): Policy = price => price + amount
  def roundToTwo: Policy = price => BigDecimal(price).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble

  // Ejemplo de estrategia compuesta: aplicar impuesto y luego redondear
  def taxedAndRounded(rate: Double): Policy = price => roundToTwo(addTax(rate)(price))

  // Estrategia por tramos (ejemplo comercial)
  def tieredStrategy(threshold: Double, highAdj: Double, lowAdj: Double): Policy =
    price => if (price >= threshold) price * (1.0 + highAdj) else price * (1.0 + lowAdj)
}
```

## Ejemplos de uso
- Datos: val prices = List(100.0, 50.0, 200.0)
- Aplicar impuesto 19%: PriceAdjuster.adjustPrices(prices, PriceAdjuster.addTax(0.19))
  - Resultado: List(119.0, 59.5, 238.0)
- Aplicar descuento 10%: PriceAdjuster.adjustPrices(prices, PriceAdjuster.discount(0.10))
  - Resultado: List(90.0, 45.0, 180.0)
- Aplicar impuesto y redondeo: PriceAdjuster.adjustPrices(prices, PriceAdjuster.taxedAndRounded(0.19))
  - Resultado: List(119.00, 59.50, 238.00)
- Estrategia por tramos: PriceAdjuster.adjustPrices(prices, PriceAdjuster.tieredStrategy(100.0, 0.10, 0.03))
  - Resultado: precios >=100 aumentan 10%, resto aumentan 3%.

# Pregunta 3

## Prompt original
Ayúdame a transformar la siguiente función anónima de scala a una función con nombre igual en el mismo lenguaje scala. Esta es la función a transformar:

val generadorIncrementadorAnonimo: Int => (Int => Int) = (incremento: Int) => (x: Int) => x + incremento

## Solución propuesta (Scala, estilo funcional)
```scala
// Versión con nombre que devuelve una función incrementadora
def generadorIncrementadorAnonimo(incremento: Int): Int => Int =
  (x: Int) => x + incremento
```

(Opcional: versión curried que se puede invocar directamente con dos parámetros)
```scala
def generadorIncrementadorAnonimo(incremento: Int)(x: Int): Int = x + incremento
```

## Ejemplos de uso
- Crear incrementador de 2: val inc2 = generadorIncrementadorAnonimo(2)
  - inc2(5) // → 7
- Llamada directa: generadorIncrementadorAnonimo(3)(4) // → 7
- Mantiene la misma semántica que la función anónima original pero con nombre y firma explícita.
