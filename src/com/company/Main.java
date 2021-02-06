package com.company;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        // Creacion del tablero
        System.out.println("Seleccione el tablero: \t1-Enunciado \t2-Personal");
        int tab = sc.nextInt();
        String namefile = "";
        switch (tab){
            case 1: namefile = "input.txt";break;
            case 2: namefile = "input2.txt";break;
            default:
        }
        Tablero t = new Tablero(namefile);
        // Declaracion de clases
        Heuristica h = null;
        Algoritmo a = null;

        System.out.println("Seleccione una heuristica: \t1-Dist. euclidea \t2-Dif. alturas \t3-Dif. fila-columna");
        int heuS = sc.nextInt();
        switch (heuS) {
            case 1: h = new H1(); break;
            case 2: h = new H2(); break;
            case 3: h = new H3(); break;
            default:
        }
        System.out.println("Seleccione un algoritmo de IA: \t1-Best First \t2-A*");
        int algS = sc.nextInt();
        switch (algS){
            case 1: a = new AlgBF(); break;
            case 2: a = new AlgA(); break;
            default:
        }
        System.out.println("ALGORITMO: " + a.getClass().getSimpleName() + "\tHEURISTICA: " + h.getClass().getSimpleName());
        a.exAlg(t, 0, 0, 9, 9, h);
    }

}
