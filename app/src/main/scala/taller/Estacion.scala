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
      }
      else if (n > principal.length) (List(), principal ++ uno, dos)
      else e

    case Uno(n) if n < 0 =>
      if (n.abs <= uno.length) {
        val (mover, resto) = uno.splitAt(n.abs)
        (principal ++ mover, resto, dos)
      }
      else if (n.abs > uno.length) (principal ++ uno, List(), dos)
      else e

    case Dos(n) if n > 0 =>
      if (n <= principal.length) {
        val (resto, mover) = principal.splitAt(principal.length - n)
        (resto, uno, mover ++ dos)
      }
      else if (n > principal.length) (List(), uno, principal ++ dos)
      else e

    case Dos(n) if n < 0 =>
      if (n.abs <= dos.length) {
        val (mover, resto) = dos.splitAt(n.abs)
        (principal ++ mover, uno, resto)
      }
      else if (n.abs > dos.length) (principal ++ dos, uno, List())
      else e

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
    def esObjetivo(e: Estado, t: Tren) : Boolean = e._1 == t

    def generarPosiblesMovimientos(e: Estado) : List[Movimiento] = {
      val (principal, uno, dos) = e
      val principalL = principal.length
      val unoL = uno.length
      val dosL = dos.length

      val movimientoUnoPos = (1 to principalL).map(Uno).toList
      val movimientoUnoNeg = (1 to unoL).map(n => Uno(-n)).toList
      val movimientoDosPos = (1 to principalL).map(Dos).toList
      val movimientoDosNeg = (1 to dosL).map(n => Dos(-n)).toList

      movimientoUnoPos ++ movimientoUnoNeg ++ movimientoDosPos ++ movimientoDosNeg
    }

    @annotation.tailrec
    def bfsRecursivo(cola: List[(Estado, Maniobra)], visitado: Set[Estado], objetivo: Tren) : Option[Maniobra] = {
      cola match {
        case Nil => None

        case (estado, camino) :: resto =>
          if (esObjetivo(estado, objetivo)) Some(camino)
          else {
            val posiblesMovimientos = generarPosiblesMovimientos(estado)

            val nuevosEstadosCaminos = for {
              movimiento <- posiblesMovimientos
              nuevoEstado = aplicarMovimiento(estado, movimiento)
              if !visitado.contains(nuevoEstado)
            } yield (nuevoEstado, camino :+ movimiento)

            val nuevoVisitado = visitado ++ nuevosEstadosCaminos.map(_._1)
            val nuevaCola = resto ++ nuevosEstadosCaminos

            bfsRecursivo(nuevaCola, nuevoVisitado, objetivo)
          }
      }
    }

    val casoBase = (t1, Nil, Nil)
    val casoObjetivo = t2

    bfsRecursivo(List((casoBase, Nil)), Set(casoBase), casoObjetivo).getOrElse(throw new Exception("No se encontró una maniobra válida"))
  }
}

