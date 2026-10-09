## Enunciado 
En un tablero de ajedrez de tamaño 8 x 8, un caballo se encuentra fijo en una de las posiciones 
del borde del tablero, por ejemplo, 1a, 1e, o 5a, entre otros, siendo el índice numérico asignado a 
las filas y el índice nominal a las columnas. 

El objetivo de la práctica es colocar 7 reinas en el tablero, de forma que ninguna de las reinas se 
ataque entre sí y ninguna pueda ser capturada por el caballo o atacar al caballo. Recordemos 
que una reina ataca a cualquier otra pieza que se encuentre en su misma fila, columna o 
diagonal, y que el caballo ataca a las casillas situadas a “salto de caballo” (en forma de L).

## Explicación del algoritmo
Se utiliza la técnica de backtracking para comprobar si al menos 7 reinas están presentes en
el tablero, con la condición extra de un caballo en una posición fija del mismo.

