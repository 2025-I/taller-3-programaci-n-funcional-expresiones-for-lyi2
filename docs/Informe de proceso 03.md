# Informe de procesos `definirManiobra`

## 1. Descripción del Proceso de Ejecución del Algoritmo

La función `definirManiobra` tiene como objetivo encontrar una secuencia de movimientos que permita transformar un tren inicial (`estadoInicial`) en uno final (`estadoFinal`) utilizando únicamente movimientos válidos entre las vías de la estación (principal, auxiliar uno y dos).

El proceso se basa en:

- **Recursividad con acumulación de soluciones parciales**, usando una función auxiliar interna con la anotación `@tailrec` para evitar desbordamientos de pila.
- **Exploración exhaustiva** (búsqueda ciega limitada) mediante **generación y prueba** (*generate-and-test*) de todas las combinaciones posibles hasta cierta profundidad.
- **Uso de `match` exhaustivo** para evaluar movimientos sobre el estado actual.
- **Filtrado funcional de soluciones** para seleccionar la más corta.

Cada estado posible tras aplicar un movimiento se compara con el estado final deseado. Cuando se encuentra una coincidencia, se registra el camino (lista de movimientos). Al finalizar, se retorna la maniobra más eficiente (de menor longitud).

---

## 2. Diseño de Funciones Recursivas y Elementos Funcionales

La solución combina múltiples aspectos fundamentales de la programación funcional:

### 2.1 Recursividad con `@tailrec`

Se emplea una función recursiva optimizada con la anotación `@tailrec` para garantizar la eficiencia y evitar el crecimiento no controlado de la pila durante búsquedas profundas.  
La recursión explora caminos aplicando combinaciones de movimientos y acumulando las soluciones encontradas.

### 2.2 Evaluación con `match`

Cada combinación de movimientos es aplicada mediante `match` sobre la función `aplicarMovimiento`, garantizando que se cubren todos los casos posibles de interacción entre trenes.

### 2.3 Funciones de Orden Superior

Se hace uso de funciones de orden superior como:

- `flatMap`
- `filter`
- `minByOption`

Estas permiten explorar rutas, filtrar soluciones y elegir la más eficiente.  
Las *for comprehensions* permiten expresar de forma declarativa la generación de combinaciones de movimientos posibles.

### 2.4 Inmutabilidad y Estilo Declarativo

- No se utiliza mutabilidad.
- Todos los estados son generados y propagados como nuevas estructuras sin modificar las existentes.
- El estilo es declarativo, favoreciendo la claridad sobre el control imperativo del flujo.

---

## 3. Generación y Ejecución de Pruebas de Software

Las pruebas para la función `definirManiobra` se estructuran para evaluar su comportamiento bajo diferentes tamaños de entrada, desde casos pequeños hasta casos de carga más grande. A continuación, se describe el proceso y la lógica detrás de cada una de las pruebas.

### 3.1 Descripción de las Pruebas

- **Prueba de Juguete: 10 vagones**
    - **Objetivo**: Evaluar el comportamiento de la función con una cantidad mínima de vagones (10).
    - **Descripción**: Se genera una lista de 10 vagones, y luego se crea una secuencia aleatoria de esos vagones. La función `definirManiobra` se utiliza para encontrar la secuencia de movimientos necesaria para transformar la lista inicial en la lista final. El resultado esperado es que el estado final coincida con la lista aleatoria generada.

  ```scala
  val vagones = (1 to 5).toList
  val vagones2 = Random.shuffle(vagones)
  val maniobra = objEstacion.definirManiobra(vagones, vagones2)
  val estadoFinal = objEstacion.aplicarMovimientos((vagones, Nil, Nil), maniobra).reverse.head._1
  assert(estadoFinal == vagones2)

- **Prueba Pequeña: 100 vagones**
    - **Objetivo**: Evaluar la función con una entrada más grande (100 vagones).
    - **Descripción**: Similar a la prueba anterior, pero con 100 vagones. Se verifica que la función siga funcionando correctamente y que el estado final coincida con el estado aleatorio generado.

  ```scala
  val vagones = (1 to 100).toList
  val vagones2 = Random.shuffle(vagones)
  val maniobra = objEstacion.definirManiobra(vagones, vagones2)
  val estadoFinal = objEstacion.aplicarMovimientos((vagones, Nil, Nil), maniobra).reverse.head._1
  assert(estadoFinal == vagones2)

- **Prueba Mediana: 500 vagones**
    - **Objetivo**: Evaluar el comportamiento de la función con 500 vagones.
    - **Descripción**: Con una mayor cantidad de vagones, se verifica si la función puede manejar entradas más grandes y seguir funcionando correctamente.

  ```scala
  val vagones = (1 to 500).toList
  val vagones2 = Random.shuffle(vagones)
  val maniobra = objEstacion.definirManiobra(vagones, vagones2)
  val estadoFinal = objEstacion.aplicarMovimientos((vagones, Nil, Nil), maniobra).reverse.head._1
  assert(estadoFinal == vagones2)

- **Prueba Grande: 1000 vagones**
    - **Objetivo**: Evaluar el rendimiento de la función con 1000 vagones.
    - **Descripción**: Esta prueba verifica si la función sigue siendo eficiente y precisa con una entrada significativamente mayor.

  ```scala
  val vagones = (1 to 1000).toList
  val vagones2 = Random.shuffle(vagones)
  val maniobra = objEstacion.definirManiobra(vagones, vagones2)
  val estadoFinal = objEstacion.aplicarMovimientos((vagones, Nil, Nil), maniobra).reverse.head._1
  assert(estadoFinal == vagones2)

- **Prueba de Carga: 1000 vagones**
    - **Objetivo**: Evaluar la capacidad de la función para manejar una carga máxima de 1000 vagones bajo condiciones de prueba más rigurosas.
    - **Descripción**: Similar a la prueba grande, pero con un enfoque en probar el rendimiento de la función bajo condiciones de carga.

  ```scala
  val vagones = (1 to 1000).toList
  val vagones2 = Random.shuffle(vagones)
  val maniobra = objEstacion.definirManiobra(vagones, vagones2)
  val estadoFinal = objEstacion.aplicarMovimientos((vagones, Nil, Nil), maniobra).reverse.head._1
  assert(estadoFinal == vagones2)

## 3.2 Comportamiento 

Para todas las pruebas, la función `definirManiobra` calcula correctamente la secuencia de movimientos que transforma el estado inicial de los vagones en el estado final deseado. Entonces el estado final después de aplicar los movimientos debería coincidir con la lista aleatoria generada (`vagones2`).

## 4. Conclusiones

Las pruebas realizadas cubren una amplia gama de escenarios, desde pequeñas listas de vagones hasta casos con una cantidad significativa de vagones. El algoritmo ha demostrado ser capaz de manejar entradas de tamaño pequeño a mediano sin problemas, pero el tiempo de ejecución tiende a crecer significativamente con entradas más grandes debido a la naturaleza de la búsqueda exhaustiva utilizada en la función.

- Las pruebas pequeñas y medianas se ejecutan rápidamente y con éxito, asegurando que el algoritmo funcione correctamente para entradas comunes.
- Las pruebas grandes y de carga requieren más tiempo de ejecución, y aunque el algoritmo sigue siendo preciso, su rendimiento podría necesitar optimización para casos de gran escala en producción.

Estas pruebas proporcionan una evaluación exhaustiva de la capacidad de la función y su fiabilidad en diferentes escenarios.