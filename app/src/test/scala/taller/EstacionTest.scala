package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import scala.util.Random

@RunWith(classOf[JUnitRunner])
class EstacionTest extends AnyFunSuite {
  val objEstacion = new Estacion()

  // Tests de ejemplo 01
  val e1 = (List('a', 'b', 'c', 'd'), Nil, Nil)
  val e2 = objEstacion.aplicarMovimiento(e1, objEstacion.Uno(2))
  val e3 = objEstacion.aplicarMovimiento(e2, objEstacion.Dos(3))
  val e4 = objEstacion.aplicarMovimiento(e3, objEstacion.Dos(-1))
  val e5 = objEstacion.aplicarMovimiento(e4, objEstacion.Uno(-2))

  test("Aplicar Movimiento 01") {
    val r1: (List[Char], collection.immutable.Nil.type, collection.immutable.Nil.type) = (List('a', 'b', 'c', 'd'), Nil, Nil)

    assert(e1 == r1)
  }

  test("Aplicar Movimiento 02") {
    val r2: objEstacion.Estado = (List('a', 'b'), List('c', 'd'), List())

    assert(e2 == r2)
  }

  test("Aplicar Movimiento 03") {
    val r3: objEstacion.Estado = (List(), List('c', 'd'), List('a', 'b'))

    assert(e3 == r3)
  }

  test("Aplicar Movimiento 04") {
    val r4: objEstacion.Estado = (List('a'), List('c', 'd'), List('b'))

    assert(e4 == r4)
  }

  test("Aplicar Movimiento 05") {
    val r5: objEstacion.Estado = (List('a', 'c', 'd'), List(), List('b'))

    assert(e5 == r5)
  }// Tests de ejemplo 01

  // Tests de ejemplo 02
  val e = (List('a', 'b'), List('c'), List('d'))

  test("Aplicar Movimientos 01") {
    val r1: (List[Char], List[Char], List[Char]) = (List('a', 'b'), List('c'), List('d'))

    assert(e == r1)
  }

  test("Aplicar Movimientos 02") {
    val ex1 = objEstacion.aplicarMovimientos(e, List(objEstacion.Uno(1), objEstacion.Dos(1), objEstacion.Uno(-2)))

    val res1: List[objEstacion.Estado] = List(
      (List('a', 'b'), List('c'), List('d')),
      (List('a'), List('b', 'c'), List('d')),
      (List(), List('b', 'c'), List('a', 'd')),
      (List('b', 'c'), List(), List('a', 'd'))
    )

    assert(ex1 == res1)
  }// Tests de ejemplo 02

  // Tests de ejemplo 03
  test ("Definir Maniobra 01") {
    val t1 = objEstacion.definirManiobra(List('a', 'b', 'c', 'd'), List('d', 'b', 'c', 'a'))
    val res2: objEstacion.Maniobra = List(objEstacion.Uno(4),
                                          objEstacion.Uno(-3),
                                          objEstacion.Dos(3),
                                          objEstacion.Uno(-1),
                                          objEstacion.Dos(-1),
                                          objEstacion.Uno(1),
                                          objEstacion.Dos(-1),
                                          objEstacion.Dos(-1),
                                          objEstacion.Uno(-1))

    assert(t1 == res2)
  } // Tests de ejemplo 03

  
  ////////////////////
   

  // Función auxiliar para generar movimientos aleatorios
  def generarMovimientos(n: Int): List[objEstacion.Movimiento] = {
    val rand = new scala.util.Random()
    (1 to n).map { _ =>
      val tipo = rand.nextBoolean()
      val cantidad = rand.nextInt(5) + 1 // entre 1 y 5
      val signo = if (rand.nextBoolean()) 1 else -1
      if (tipo) objEstacion.Uno(signo * cantidad) else objEstacion.Dos(signo * cantidad)
    }.toList
  }

  // aplicarMovimiento Prueba de juguete (10 vagones, 10 movimientos)
  test("01 aplicarMovimiento Prueba de Juguete: 10 vagones y 10 movimientos") {
    val vagones = (1 to 10).toList
    val estadoInicial: objEstacion.Estado = (vagones, Nil, Nil)
    val movimientos = generarMovimientos(10)
    val resultado = movimientos.scanLeft(estadoInicial)(objEstacion.aplicarMovimiento)

    assert(resultado.head == estadoInicial)
    assert(resultado.length == 11)
  }
  
  // aplicarMovimiento Prueba pequeña (100 vagones y 100 movimientos)
  test("02 aplicarMovimiento Prueba Pequeña: 100 vagones y 100 movimientos") {
    val vagones = (1 to 100).toList
    val estadoInicial: objEstacion.Estado = (vagones, Nil, Nil)
    val movimientos = generarMovimientos(100)

    val inicio = System.nanoTime() // ⏱️ Inicio del cronómetro

    val resultado = movimientos.scanLeft(estadoInicial)(objEstacion.aplicarMovimiento)
    
    val fin = System.nanoTime() // ⏱️ Fin del cronómetro
    val duracionMs = (fin - inicio) / 1_000_000 // conversión a milisegundos

    println(s"⏱️ Tiempo de ejecución Prueba Pequeña: $duracionMs ms")

    assert(resultado.head == estadoInicial)
    assert(resultado.length == 101)
}
  
  // aplicarMovimiento Prueba mediana (500 vagones y 500 movimientos)
  test("03 aplicarMovimiento Prueba Mediana: 500 vagones y 500 movimientos") {
    val vagones = (1 to 500).toList
    val estadoInicial: objEstacion.Estado = (vagones, Nil, Nil)
    val movimientos = generarMovimientos(500)

  val inicio = System.nanoTime() // ⏱️ Inicio del cronómetro

  val resultado = movimientos.scanLeft(estadoInicial)(objEstacion.aplicarMovimiento)

  val fin = System.nanoTime() // ⏱️ Fin del cronómetro
  val duracionMs = (fin - inicio) / 1_000_000 // conversión a milisegundos

  println(s"⏱️ Tiempo de ejecución Prueba Mediana: $duracionMs ms")

  assert(resultado.head == estadoInicial)
  assert(resultado.length == 501)
}

  // aplicarMovimiento Prueba grande (1000 vagones y 1000 movimientos) //
  test("04 aplicarMovimiento Prueba Grande: 1000 vagones y 1000 movimientos") {
    val vagones = (1 to 1000).toList
    val estadoInicial: objEstacion.Estado = (vagones, Nil, Nil)
    val movimientos = generarMovimientos(1000)

  val tiempoInicio = System.nanoTime() // 🔹 Empieza medición

  val resultado = movimientos.scanLeft(estadoInicial)(objEstacion.aplicarMovimiento)

  val tiempoFin = System.nanoTime() // 🔹 Termina medición

  val tiempoTotalMs = (tiempoFin - tiempoInicio) / 1_000_000.0 // 🔹 En milisegundos

  println(f"Tiempo de ejecución Prueba Grande: $tiempoTotalMs%.3f ms")

  assert(resultado.head == estadoInicial)
  assert(resultado.length == 1001)
}
  
  // aplicarMovimiento Prueba de carga (1000 vagones y 1000 movimientos)
  test("05 aplicarMovimiento Prueba de Carga: 1000 vagones y 1000 movimientos") {
    val vagones = (1 to 1000).toList
    val estadoInicial: objEstacion.Estado = (vagones, Nil, Nil)
    val movimientos = generarMovimientos(1000)
    val resultado = movimientos.scanLeft(estadoInicial)(objEstacion.aplicarMovimiento)

    assert(resultado.head == estadoInicial)
    assert(resultado.length == 1001)
  }

  // aplicarMovimientos Prueba de juguete (10 vagones, 10 movimientos)
  test("01 aplicarMovimientos Prueba de Juguete: 10 vagones y 10 movimientos") {
    val vagones = (1 to 10).toList
    val estadoInicial: objEstacion.Estado = (vagones, Nil, Nil)
    val movimientos = generarMovimientos(10)
    val f1 = objEstacion.aplicarMovimientos(estadoInicial, movimientos)
    val res1 = movimientos.scanLeft(estadoInicial)(objEstacion.aplicarMovimiento)

    assert(f1 == res1)
  }

  // aplicarMovimientos Prueba pequeña (100 vagones y 100 movimientos)
  test("02 aplicarMovimientos Prueba Pequeña: 100 vagones y 100 movimientos") {
    val vagones = (1 to 100).toList
    val estadoInicial: objEstacion.Estado = (vagones, Nil, Nil)
    val movimientos = generarMovimientos(100)
    val f2 = objEstacion.aplicarMovimientos(estadoInicial, movimientos)
    val res2 = movimientos.scanLeft(estadoInicial)(objEstacion.aplicarMovimiento)

    assert(f2 == res2)
  }

  // aplicarMovimientos Prueba mediana (500 vagones y 500 movimientos)
  test("03 aplicarMovimientos Prueba Mediana: 500 vagones y 500 movimientos") {
    val vagones = (1 to 500).toList
    val estadoInicial: objEstacion.Estado = (vagones, Nil, Nil)
    val movimientos = generarMovimientos(500)
    val f3 = objEstacion.aplicarMovimientos(estadoInicial, movimientos)
    val res3 = movimientos.scanLeft(estadoInicial)(objEstacion.aplicarMovimiento)

    assert(f3 == res3)
  }

  // aplicarMovimientos Prueba grande (1000 vagones y 1000 movimientos)
  test("04 aplicarMovimientos Prueba Grande: 1000 vagones y 1000 movimientos") {
    val vagones = (1 to 1000).toList
    val estadoInicial: objEstacion.Estado = (vagones, Nil, Nil)
    val movimientos = generarMovimientos(1000)
    val f4 = objEstacion.aplicarMovimientos(estadoInicial, movimientos)
    val res4 = movimientos.scanLeft(estadoInicial)(objEstacion.aplicarMovimiento)

    assert(f4 == res4)
  }

  // aplicarMovimientos Prueba de carga (1000 vagones y 1000 movimientos)
  test("05 aplicarMovimientos Prueba de Carga: 1000 vagones y 1000 movimientos") {
    val vagones = (1 to 1000).toList
    val estadoInicial: objEstacion.Estado = (vagones, Nil, Nil)
    val movimientos = generarMovimientos(1000)
    val f5 = objEstacion.aplicarMovimientos(estadoInicial, movimientos)
    val res5 = movimientos.scanLeft(estadoInicial)(objEstacion.aplicarMovimiento)

    assert(f5 == res5)
  }
  // definirManiobra Prueba de juguete (10 vagones)
  test("01 definirManiobra Prueba de Juguete: 10 vagones") {
    val vagones = (1 to 5).toList
    val vagones2 = Random.shuffle(vagones)
    val maniobra = objEstacion.definirManiobra(vagones, vagones2)
    val estadoFinal = objEstacion.aplicarMovimientos((vagones, Nil, Nil), maniobra).reverse.head._1

    assert(estadoFinal == vagones2)
  }

  // definirManiobra Prueba pequeña (100 vagones)
  test("02 definirManiobra Prueba Pequeña: 100 vagones") {
    val vagones = (1 to 100).toList
    val vagones2 = Random.shuffle(vagones)
    val maniobra = objEstacion.definirManiobra(vagones, vagones2)
    val estadoFinal = objEstacion.aplicarMovimientos((vagones, Nil, Nil), maniobra).reverse.head._1

    assert(estadoFinal == vagones2)
  }

  // definirManiobra Prueba mediana (500 vagones)
  test("03 definirManiobra Prueba Mediana: 500 vagones") {
    val vagones = (1 to 500).toList
    val vagones2 = Random.shuffle(vagones)
    val maniobra = objEstacion.definirManiobra(vagones, vagones2)
    val estadoFinal = objEstacion.aplicarMovimientos((vagones, Nil, Nil), maniobra).reverse.head._1

    assert(estadoFinal == vagones2)
  }

  // definirManiobra Prueba grande (1000 vagones)
  test("04 definirManiobra Prueba Grande: 1000 vagones") {
    val vagones = (1 to 1000).toList
    val vagones2 = Random.shuffle(vagones)
    val maniobra = objEstacion.definirManiobra(vagones, vagones2)
    val estadoFinal = objEstacion.aplicarMovimientos((vagones, Nil, Nil), maniobra).reverse.head._1

    assert(estadoFinal == vagones2)
  }

  // definirManiobra Prueba de carga (1000 vagones)
  test("05 definirManiobra Prueba de Carga: 1000 vagones") {
    val vagones = (1 to 1000).toList
    val vagones2 = Random.shuffle(vagones)
    val maniobra = objEstacion.definirManiobra(vagones, vagones2)
    val estadoFinal = objEstacion.aplicarMovimientos((vagones, Nil, Nil), maniobra).reverse.head._1

    assert(estadoFinal == vagones2)
  }


}
