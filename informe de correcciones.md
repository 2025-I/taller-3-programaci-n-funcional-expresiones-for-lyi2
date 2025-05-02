# Informe de Correcciones: Función `aplicarMovimiento`

La función `aplicarMovimiento` implementa la lógica para mover vagones entre tres trenes (`principal`, `uno`, `dos`) según un movimiento especificado. A continuación, se presenta un análisis detallado de la función, identificando posibles problemas, mejoras y recomendaciones.

---

## 1. Análisis de la Lógica
La función utiliza un patrón `match` para manejar los casos de movimientos `Uno` y `Dos`, tanto positivos como negativos. La lógica parece correcta en términos de dividir y combinar listas para simular el movimiento de vagones. Sin embargo, hay algunos puntos que podrían mejorarse:

- **Caso Base (`case _ => e`)**: Este caso captura cualquier movimiento no reconocido. Aunque es útil como medida de seguridad, sería mejor lanzar una excepción o manejar explícitamente los casos inválidos para evitar silencios en errores.
  
- **Redundancia en la Lógica**: Los bloques de código para `Uno(n)` y `Dos(n)` son muy similares. Esto podría refactorizarse para evitar duplicación y mejorar la mantenibilidad.

---

## 2. Posibles Problemas
- **Validación de Entradas**: No se valida explícitamente que el valor de `n` sea coherente con el estado actual de los trenes. Por ejemplo:
  - Si `n` es mayor que la longitud de `principal`, se maneja correctamente, pero no hay un mensaje de advertencia o registro que indique que se intentó un movimiento inválido.
  - Si `n == 0`, no se especifica un comportamiento claro. Actualmente, parece que el estado no cambia, pero esto no está documentado.

- **Eficiencia**: La función utiliza operaciones como `splitAt` y concatenación (`++`), que pueden ser costosas en términos de rendimiento para listas grandes. Si se espera trabajar con grandes cantidades de datos, podría ser necesario optimizar estas operaciones.

- **Falta de Documentación**: No hay comentarios que expliquen el propósito de cada caso en el `match`. Esto dificulta la comprensión del código para otros desarrolladores.

---

## 3. Recomendaciones de Mejora

### 3.1 Refactorización para Reducir Redundancia
Los casos `Uno(n)` y `Dos(n)` pueden combinarse en una función auxiliar que reciba como parámetro la lista objetivo (`uno` o `dos`). Esto reduciría la duplicación de código.

Ejemplo de refactorización:
```scala
private def moverVagones(origen: Tren, destino: Tren, n: Int): (Tren, Tren) = {
  if (n > 0) {
    if (n <= origen.length) {
      val (resto, mover) = origen.splitAt(origen.length - n)
      (resto, mover ++ destino)
    } else (List(), origen ++ destino)
  } else {
    if (n.abs <= destino.length) {
      val (mover, resto) = destino.splitAt(n.abs)
      (origen ++ mover, resto)
    } else (origen ++ destino, List())
  }
}


