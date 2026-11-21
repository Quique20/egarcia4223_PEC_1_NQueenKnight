package Classes;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Posicion {
    private final int _fila;
    private final int _col;

    public Posicion(int fila, int col){
        this._fila = fila;
        this._col = col;
    }

    public int getFila(){
        return _fila;
    }

    public int getCol(){
        return _col;
    }

    // Dicta si las posiciones son válidas o no
    public static Posicion parsePosition(String input, int _tamanyoTablero) {
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

    // Precalcula las posiciones del caballo y las guarda en un set
    public static Set<Posicion> PrecalcularPosicionesCaballo(Posicion caballo, int _tamanyoTablero) {

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Posicion)) return false;

        Posicion otra = (Posicion) obj;

        return _fila == otra.getFila()
                && _col == otra.getCol();
    }

    @Override
    public int hashCode() {
        return Objects.hash(_fila, _col);
    }

}
