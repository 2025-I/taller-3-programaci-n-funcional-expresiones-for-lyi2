package taller

class Estacion() {
  type Vagon = Any
  type Tren = List[Vagon]
  type Estado = (Tren, Tren, Tren)

  trait Movimiento
  case class Uno(n: Int) extends Movimiento
  case class Dos(n: Int) extends Movimiento

  type Maniobra = List[Movimiento]

  def aplicarMovimiento(e: Estado, m: Movimiento): Estado = {
  val (principal, uno, dos) = e

  m match {
    case Uno(n) if n > 0 =>
      if (n <= principal.length) {
        val (resto, mover) = principal.splitAt(principal.length - n)
        (resto, mover ++ uno, dos)
      } else e

    case Uno(n) if n < 0 =>
      if (n.abs <= uno.length) {
        val (mover, resto) = uno.splitAt(n.abs)
        (principal ++ mover, resto, dos)
      } else e

    case Dos(n) if n > 0 =>
      if (n <= principal.length) {
        val (resto, mover) = principal.splitAt(principal.length - n)
        (resto, uno, mover ++ dos)
      } else e

    case Dos(n) if n < 0 =>
      if (n.abs <= dos.length) {
        val (mover, resto) = dos.splitAt(n.abs)
        (principal ++ mover, uno, resto)
      } else e

    case _ => e
  }
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
