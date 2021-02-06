package com.company;

public class Casilla {
    int fila;
    int columna;
    int altura;
    boolean visitado;
    String camino = "";
    double tiempo;
    double valor;
    double h;
    boolean visited;

    public Casilla(int i, int j, int c){
        this.fila = i;
        this.columna = j;
        this.altura = c;
        visited=false;
    }

    @Override
    public String toString() {
        return Integer.toString(altura);
    }

    public int getAltura() {
        return altura;
    }

    public void agregaOp(String nomFunc){
        this.camino = this.camino + nomFunc;
    }
    public static double getDesnivel(Casilla ini, Casilla fin){
        int dif;
        if((dif = ini.altura - fin.altura) > 0)
            return 0.5;
        else
            return 1-dif;
    }
}
