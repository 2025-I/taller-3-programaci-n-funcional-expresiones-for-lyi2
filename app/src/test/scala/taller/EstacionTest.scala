package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

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
    val r3: objEstacion.Estado = (List('a', 'b'), List('c', 'd'), List())

    assert(e3 == r3)
  }

  test("Aplicar Movimiento 04") {
    val r4: objEstacion.Estado = (List('a', 'b'), List('c', 'd'), List())

    assert(e4 == r4)
  }

  test("Aplicar Movimiento 05") {
    val r5: objEstacion.Estado = (List('a', 'b'), List('c', 'd'), List())

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
}
