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
    def aplicarMovimientosAux(movs:Maniobra, acc:List[Estado]):List[Estado] = {
      if (movs.isEmpty) List((List(), List(), List()))
      else aplicarMovimientosAux(movs.tail, acc ++ List((List(), List(), List())))
    }

    aplicarMovimientosAux(movs, List(e))
  }

  def definirManiobra(t1: Tren, t2: Tren) : Maniobra = {
      List(Uno(1), Dos(2))
  }
}
