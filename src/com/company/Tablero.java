package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Tablero {
    Casilla [][] casilla;
    Casilla casillaAct;
    int numFilas, numCols;
    int fAct, cAct;

    public Tablero(String namefile) throws IOException {
        String cadena;
        int i = 0, j = 0, numFilas = 1, numCols = 0;
        // Contar filas & columnas
        BufferedReader br = new BufferedReader(new FileReader(namefile));
        cadena = br.readLine();
        StringTokenizer st = new StringTokenizer(cadena, " ");
        numCols = st.countTokens();
        while ((cadena = br.readLine()) != null) {
            numFilas++;
        }
        this.numCols = numCols;
        this.numFilas = numFilas;
        Casilla [][] c = new Casilla[numFilas][numCols];
        br.close();
        // Guardar tablero en clase específica desde fichero
        br = new BufferedReader(new FileReader(namefile));
        while ((cadena = br.readLine()) != null) {
            j = 0;
            st = new StringTokenizer(cadena, " ");
            while (st.hasMoreElements()) {
                String token = st.nextToken();
                if (token.equals("*"))
                    c[i][j] = new Casilla(i, j, -1);
                else
                    c[i][j] = new Casilla(i, j, Integer.parseInt(token));
                j++;
            }
            i++;
        }
        br.close();
        this.casilla = c;
    }

    @Override
    public String toString() {
        String s = new String();
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numCols; j++) {
                s = s + casilla[i][j].toString() + "\t";
            }
            s = s + "\n";
        }
        return s;
    }

    public Casilla comprobarArribaBF(){
        if(fAct > 0 && casilla[fAct-1][cAct].getAltura() != -1 && !casilla[fAct-1][cAct].visited){
            casilla[fAct-1][cAct].visited=true;
            return casilla[fAct-1][cAct];
        }

        else
            return null;
    }
    public Casilla comprobarAbajoBF(){
        if(fAct < numFilas-1 && casilla[fAct+1][cAct].getAltura() != -1 &&  !casilla[fAct+1][cAct].visited){
            casilla[fAct+1][cAct].visited=true;
            return casilla[fAct+1][cAct];
        }

        else
            return null;
    }
    public Casilla comprobarIzdaBF(){
        if(cAct > 0 && casilla[fAct][cAct-1].getAltura() != -1 && !casilla[fAct][cAct-1].visited){
            casilla[fAct][cAct-1].visited=true;
            return casilla[fAct][cAct-1];
        }

        else
            return null;
    }
    public Casilla comprobarDchaBF(){
        if(cAct < numCols-1 && casilla[fAct][cAct+1].getAltura() != -1 && !casilla[fAct][cAct+1].visited){
            casilla[fAct][cAct+1].visited = true;
            return casilla[fAct][cAct+1];
        }

        else
            return null;
    }
    public Casilla comprobarArribaA(){
        if(fAct > 0 && casilla[fAct-1][cAct].getAltura() != -1)
            return casilla[fAct-1][cAct];
        else
            return null;
    }
    public Casilla comprobarAbajoA(){
        if(fAct < numFilas-1 && casilla[fAct+1][cAct].getAltura() != -1)
            return casilla[fAct+1][cAct];
        else
            return null;
    }
    public Casilla comprobarIzdaA(){
        if(cAct > 0 && casilla[fAct][cAct-1].getAltura() != -1)
            return casilla[fAct][cAct-1];
        else
            return null;
    }
    public Casilla comprobarDchaA(){
        if(cAct < numCols-1 && casilla[fAct][cAct+1].getAltura() != -1)
            return casilla[fAct][cAct+1];
        else
            return null;
    }
    public void piezaAPosicion(int fila, int col){
        this.fAct = fila;
        this.cAct = col;
    }
    public void resetTablero(){
        for(int i=0; i<this.numFilas; i++){
            for(int j=0; j<this.numCols; j++){
                this.casilla[i][j].visited = false;
                this.casilla[i][j].tiempo = 0;
                this.casilla[i][j].camino = "";
                this.casilla[i][j].valor = 0;
            }
        }
        this.casillaAct = null;
    }
    public Casilla getCasillaByPos(int fila, int col){
        return casilla[fila][col];
    }

}
