# Informe de procesos de `aplicarMovimientos`

## 1. Descripción del proceso de ejecucion de algoritmo

El algoritmo implementado en la función `aplicarMovimientos` simula una serie de desplazamientos realizados sobre los vagones.

El algoritmo ejecuta `aplicarMovimientos` que llama `aplicarMovimientosAux` en una funcion recursiva para cada movimiento de la lista de movimientos. La función `aplicarMovimiento` se encarga de aplicar un movimiento a un vagón específico, y luego llama a sí misma para aplicar el siguiente movimiento. La función `aplicarMovimientoAux` se encarga de aplicar el movimiento a un vagón específico y luego llama recursivamente a sí misma para aplicar el siguiente movimiento.

## 2. Diseño de funciones recursivas y elementos funcionales

La función `aplicarMovimientosAux` es una función recursiva que toma un vagón y un movimiento como argumentos. La función aplica el movimiento al vagón y luego lo acumula al primer elemento de la lista `acc`

Las funciones usadas en el Test del proceso previo de la funcion `aplicarMovimiento` contienen el mismo funcionamiento de la funcion, permitiendo asi realizar los tsts eficientemente

## 3. Generación de pruebas de software

Se diseñaron pruebas automaticas con tamaños crecientes para evaluar el rendimiento y robustez del algoritmo mediante:

* Pruebas de juguete: 10 vagones
* Prueba pequeña: 100 vagones
* Prueba mediana: 500 vagones
* Prueba grande: 1000 vagones