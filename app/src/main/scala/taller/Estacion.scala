package taller

class Estacion() {
  type Vagon = Any
  type Tren = List[Vagon]
  type Estado = (Tren, Tren, Tren)

  trait Movimiento
  case class Uno(n: Int) extends Movimiento
  case class Dos(n: Int) extends Movimiento

  type Maniobra = List[Movimiento]

  def aplicarMovimiento(e: Estado, m: Movimiento = Uno(0)) : Estado = {
    (List(), List(), List())
  }

  def aplicarMovimientos(e: Estado, movs:Maniobra) : List[Estado] = {
    @annotation.tailrec
    def aplicarMovimientosAux(movs:Maniobra, acc:List[Estado]) : List[Estado] = movs match {
      case Nil => acc
      case x :: xs => aplicarMovimientosAux(xs, aplicarMovimiento(acc.head, x) :: acc)
    }

    aplicarMovimientosAux(movs, List(e)).reverse
  }

  def definirManiobra(t1: Tren, t2: Tren) : Maniobra = {
      List(Uno(1), Dos(2))
  }
}
