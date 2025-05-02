# Informe de Procesos 
## 1. Descripción del Proceso de Ejecución de los Algoritmos

El algoritmo implementado en la función `aplicarMovimiento` simula el desplazamiento de vagones entre tres listas las cuales representan una estación ferroviaria: la vía principal, la vía auxiliar `uno` y la vía auxiliar `dos`. La función aplica un movimiento sobre un estado inicial, desplazando una cantidad específica de vagones desde o hacia una de las vías auxiliares.

Cada movimiento es de tipo `Uno(n)` o `Dos(n)`:
- Si `n > 0`, se mueven vagones **desde la vía principal** hacia la vía auxiliar.
- Si `n < 0`, se mueven vagones **desde la vía auxiliar** hacia la vía principal.

El algoritmo se ejecuta en una estructura de patrón `match`, garantizando claridad y exhaustividad en los casos posibles, usando así expresiones for.

## 2. Diseño de Funciones Recursivas y Elementos Funcionales

Aunque `aplicarMovimiento` no es una función recursiva en sí, la ejecución secuencial de múltiples movimientos se realiza usando la función funcional **`scanLeft`**, lo cual es una técnica común en programación funcional para mantener el estado entre transformaciones que son sucesivas. De ahí, su uso permite recorrer la lista de movimientos acumulando los estados intermedios generados tras aplicar cada  uno de los movimientos.

Además, para la generación de datos, se utiliza una función funcional `map` combinada con `toList`, lo que mantiene el estilo declarativo de Scala.

## 3. Generación de Pruebas de Software

Se diseñaron pruebas automáticas con tamaños crecientes para evaluar el rendimiento y robustez del algoritmo mediante :

- **Prueba de Juguete**: 10 vagones, 10 movimientos.
- **Prueba Pequeña**: 100 vagones, 100 movimientos.
- **Prueba Mediana**: 500 vagones, 500 movimientos.
- **Prueba Grande**: 1000 vagones, 1000 movimientos.

Para cada prueba:
- Se genera una lista inicial de vagones numerados consecutivamente.
- Se generan movimientos aleatorios mediante la función `generarMovimientos(n)`.
- Se evalúa el resultado tras aplicar los movimientos usando `scanLeft`.
- Se verifica que el estado inicial esté correctamente representado y que la longitud del resultado coincida con el número de pasos esperados (`n + 1`).
### Ejemplo de función generadora:
- Se genera una funcion ramdon para generar las pruebas
```scala
def generarMovimientos(n: Int): List[objEstacion.Movimiento] = {
  val rand = new scala.util.Random()
  (1 to n).map { _ =>
    val tipo = rand.nextBoolean()
    val cantidad = rand.nextInt(5) + 1
    val signo = if (rand.nextBoolean()) 1 else -1
    if (tipo) objEstacion.Uno(signo * cantidad)
    else objEstacion.Dos(signo * cantidad)
  }.toList
}
 ## 4. Evaluación de Tiempos de Ejecución y Conclusiones
A continuación se presenta el resultado obtenido para la prueba (Prueba Grande):

### Prueba Pequeña: 100 vagones y 100 movimientos

- **Tamaño de entrada**: 100 vagones y 100 movimientos aleatorios.
- **Tiempo de ejecución medido**: 2 milisegundos.
- **Resultado del test**: exitoso (1 test ejecutado, 1 test aprobado).
- **Duración total del suite de pruebas**: 44 milisegundos.

### Detalles de Ejecución

- **Tiempo de ejecución total**: 44ms
- **Resultado**: Todos los tests en `taller.EstacionTest` fueron exitosos.

 ### Prueba Pequeña: 500 vagones y 500 movimientos
- **Tamaño de entrada**: 500 vagones y 500 movimientos aleatorios.
- **Tiempo de ejecución medido**: 18 milisegundos.
- **Resultado del test**: exitoso (1 test ejecutado, 1 test aprobado).
- **Duración total del suite de pruebas**: 60 milisegundos.

### Detalles de Ejecución

- **Tiempo de ejecución total**: 60ms
- **Resultado**: Todos los tests en `taller.EstacionTest` fueron exitosos.

- **Tamaño de entrada**: 1000 vagones y 1000 movimientos aleatorios.
- **Tiempo de ejecución medido**: 62.966 milisegundos.
- **Resultado del test**: exitoso (1 test ejecutado, 1 test aprobado).
- **Duración total del suite de pruebas**: 0.13 segundos.

### Detalles de Ejecución

- **Tiempo de ejecución total**: 62ms
- **Resultado**: Todos los tests en `taller.EstacionTest` fueron exitosos.

### Conclusiones

- El algoritmo muestra **estabilidad y consistencia** en la aplicación secuencial de movimientos, gracias al uso funcional de `scanLeft`.
- El tiempo de ejecución se mantiene en un rango eficiente incluso con entradas grandes, siendo menor a 70 ms para 1000 elementos, lo cual es adecuado para simulaciones de tamaño medio.
- La **complejidad general es lineal** respecto al número de movimientos aplicados, ya que `scanLeft` aplica la transformación de manera progresiva.
- Para entradas más grandes o aplicaciones en tiempo real, podrían explorarse mejoras como:
  - Uso de estructuras mutables para listas.
  - Paralelización o procesamiento asíncrono de movimientos.
- La estructura actual es ideal para tareas educativas o de simulación con fines de análisis, donde la claridad y corrección son más importantes que la optimización extrema.

En conclusión, el algoritmo es correcto, funcional y eficiente bajo condiciones normales de uso, destacando por su diseño claro y el aprovechamiento de técnicas de programación funcional.


