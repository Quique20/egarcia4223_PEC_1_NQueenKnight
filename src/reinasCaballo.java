import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import Classes.Posicion;

public class reinasCaballo {

    public static final int _tamanyoTablero = 8;
    private static Set<Integer> _cols; // Columnas usadas por las reina
    private static Set<Integer> _negDiag; // (r - c) Diagonal negativa de las reinas
    private static Set<Integer> _posDiag; // (r + c ) Diagonal positiva de las reinas
    private static Set<Posicion> _knightPos; // Posiciones precalculadas del caballo
    private static final Set<Character> _traduccion = new HashSet(
            Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'));
    private static boolean[][] tablero = new boolean[_tamanyoTablero][_tamanyoTablero]; // Tablero base
    private static Posicion _caballo;

    private static int count = 0;

    // Test variables
    private static final String posicionCaballoTest = "1h";

    public static void main(String args[]) {
        // Inicializamos los restricciones
        _cols = new HashSet<Integer>();
        _negDiag = new HashSet<Integer>();
        _posDiag = new HashSet<Integer>();
        _caballo = parsePosition(posicionCaballoTest);
        _knightPos = PrecalcularPosicionesCaballo(_caballo);

        // Comienza el algoritmo
        SolveNQueens(0, 0);

        System.out.println(count);
    }

    private static void SolveNQueens(int fila, int reinasColocadas) {
        // Ya hemos recorrido las 8 filas
        if (fila == _tamanyoTablero) {
            if (reinasColocadas == 7) {
                count++;
            }
            return;
        }

        // Podamos si ya no es posible colocar 7 reinas
        if (reinasColocadas > 7
                || reinasColocadas + (_tamanyoTablero - fila) < 7) {
            return;
        }

        // Opción 1: dejar esta fila sin reina
        SolveNQueens(fila + 1, reinasColocadas);

        for (int j = 0; j < _tamanyoTablero; j++) {
            // Hay una reina que vulnera, continua
            if (_cols.contains(j)
                    || _negDiag.contains(fila - j)
                    || _posDiag.contains(fila + j)
                    || _knightPos.contains(new Posicion(fila, j))
                    || j == _caballo.getCol()
                    || Math.abs(fila - _caballo.getFila()) == Math.abs(j - _caballo.getCol())) {
                continue;
            }

            _cols.add(j);
            _negDiag.add(fila - j);
            _posDiag.add(fila + j);

            SolveNQueens(fila + 1, reinasColocadas + 1);
            // Backtracking - quitamos los valores
            _cols.remove(j);
            _negDiag.remove(fila - j);
            _posDiag.remove(fila + j);
        }
    }

    private static Set<Posicion> PrecalcularPosicionesCaballo(Posicion caballo) {

        Set<Posicion> posiciones = new HashSet<>();
        int[] df = { -2, -2, -1, -1, 1, 1, 2, 2 };
        int[] dc = { -1, 1, -2, 2, -2, 2, -1, 1 };
        for (int i = 0; i < 8; i++) {
            int fila = caballo.getFila() + df[i];
            int columna = caballo.getCol() + dc[i];
            if (fila >= 0 && fila < _tamanyoTablero
                    && columna >= 0 && columna < _tamanyoTablero) {
                posiciones.add(new Posicion(fila, columna));
            }
        }
        return posiciones;
    }

    private static Posicion parsePosition(String input) {
        input = input.trim().toLowerCase();

        if (input.length() != 2) {
            throw new IllegalArgumentException(
                    "Formato inválido. Ejemplo: 1e");
        }

        int fila = Character.getNumericValue(input.charAt(0)) - 1;
        int columna = input.charAt(1) - 'a';

        if (fila < 0 || fila >= _tamanyoTablero
                || columna < 0 || columna >= _tamanyoTablero) {
            throw new IllegalArgumentException(
                    "Posición fuera del tablero: " + input);
        }

        return new Posicion(fila, columna);
    }

}
