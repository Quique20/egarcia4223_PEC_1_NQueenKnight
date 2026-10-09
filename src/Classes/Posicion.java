package Classes;

import java.util.Objects;

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
