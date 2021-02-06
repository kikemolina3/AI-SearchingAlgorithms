package com.company;

// Primera heuristica - Distancia euclidea de casilla actual a destino
public class H1 extends Heuristica{
    @Override
    public double calcHeuristica(Casilla c1, Casilla c2) {
        return Math.sqrt(Math.pow(c1.fila - c2.fila, 2) + Math.pow(c1.columna - c2.columna, 2));
    }
}
