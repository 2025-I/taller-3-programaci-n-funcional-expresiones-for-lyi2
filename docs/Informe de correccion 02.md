# 1.2.2 Aplicar movimientos

## Demostracion de `aplicarMovimientos`

Considerando la funcion `aplicarMovimientos`:

```scala
def aplicarMovimientos(e: Estado, movs:Maniobra) : List[Estado] = {
    @annotation.tailrec
    def aplicarMovimientosAux(movs:Maniobra, acc:List[Estado]) : List[Estado] = movs match {
      case Nil => acc
      case x :: xs => aplicarMovimientosAux(xs, aplicarMovimiento(acc.head, x) :: acc)
    }

    aplicarMovimientosAux(movs, List(e)).reverse
  }
```

La funcion `aplicarMovimientos` recibe un `Estado` y una `Maniobra` (una lista de `Movimiento`) y devuelve una lista de `Estado` que representa el resultado de aplicar cada movimiento de la maniobra al estado inicial.
Entrega una lista de `Estado` y la `Maniobra` a la funcion `aplicarMovimientosAux`, que es una funcion auxiliar recursiva que aplica cada movimiento de la maniobra al estado actual y acumula los resultados en una lista.

## Demostracion de `aplicarMovimientosAux`

Considerando la funcion `aplicarMovimientosAux`:

```scala
@annotation.tailrec
def aplicarMovimientosAux(movs:Maniobra, acc:List[Estado]) : List[Estado] = movs match {
  case Nil => acc
  case x :: xs => aplicarMovimientosAux(xs, aplicarMovimiento(acc.head, x) :: acc)
}
```

La funcion `aplicarMovimientosAux` aplica recursivamente cada movimiento al estado actual y los acumula en una lista. La recursividad es de cola, lo que significa que la llamada recursiva es la última operación realizada en cada paso. Esto permite que el compilador optimice la recursión y evite el desbordamiento de pila en casos de listas grandes.

Se implementa el siguiente proceso

* Un estado $s = (movs, acc)$, en el cual $movs$ es la lista de movimientos y $acc$ es la lista de estados acumulados. Donde $movs = List(m_i, m_{i+1}, ..., m_n)$ y $acc = List(e_k, ..., e_1, e_0)$ y $m$ es un tipo `Movimiento` y $e$ es un tipo `Estado`.
* Un estado inicial $s_0 = (List(m_1, m_2, ..., m_n), List(e_0))$ donde $e_0$ es el estado inicial.
* $(movs, acc)$ es final si $movs$ es vacio, es decir, $movs = Nil$. En este caso, el resultado es $acc$.
* La invariante del ciclo es $Inv(movs, acc) \equiv (movs, acc) = (List(m_i, m_{i+1}, ..., m_n), List(e_k, ..., e_1, e_0)) \wedge n = k$
* $transformar(movs, acc) = (movs.tail, aplicarMovimiento(movs.head, acc.head) :: acc)$

Ahora se demuestran los puntos:

* $Inv(s_0)$:

$$s_0 = (List(m_1, m_2, ..., m_n), List(e_0)) \Rightarrow n = k$$

* $s_i \neq s_f \wedge Inv(s_i) \rightarrow Inv((transformar(s_i)))$

$$(movs, acc_i) = (List(m_i, m_{i+1}, ..., m_n), List(e_k, ..., e_1, e_0)) \rightarrow (movs.tail, acc_{i+1}) = (List(m_{i+1}, m_{i+2}, ..., m_n), List(e_{k+1}, ..., e_1, e_0))$$

* $Inv(s_f) \rightarrow respuesta(s_f) == f(a)$

$$Inv(List(), List(e_n, ..., e_1, e_0)) \rightarrow n = k$$

* En cada paso de la lista $movs$ se reduce hasta ser vacia