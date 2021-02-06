package com.company;

public class H3 extends Heuristica{
    @Override
    public double calcHeuristica(Casilla c1, Casilla c2) {
        return (Math.abs(c1.fila - c2.fila) + Math.abs(c1.columna - c2.columna))/2;
    }
}
