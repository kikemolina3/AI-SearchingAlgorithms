package com.company;

public class H2 extends Heuristica{
    @Override
    public double calcHeuristica(Casilla c1, Casilla c2) {
        return Math.abs(c2.altura - c1.altura)*5;
    }
}
