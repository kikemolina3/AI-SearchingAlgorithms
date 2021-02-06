package com.company;
import java.util.*;

public class AlgBF extends Algoritmo{
    @Override
    public boolean exAlg(Tablero t, int fIni, int cIni, int fFin, int cFin, Heuristica h) {
            boolean encontrado = false;
            Casilla f = t.getCasillaByPos(fFin, cFin);
            float i = 0;
            // q : cola de casillas pendientes
            LinkedList<Casilla> q = new LinkedList<Casilla>();
            q.add(t.casilla[fIni][cIni]);
            // l : lista de casillas tratadas
            LinkedList<Casilla> l = new LinkedList<Casilla>();
            while (!encontrado && !q.isEmpty()) {
                // Desencola casilla a tratar
                t.casillaAct = q.remove();
                t.piezaAPosicion(t.casillaAct.fila, t.casillaAct.columna);
                // Fin si encuentra casilla final
                if (t.casillaAct.equals(t.casilla[fFin][cFin])) {
                    encontrado = true;
                } else {
                    // Despliegue lista de casillas 'hijas' 
                    // lk : lista de casillas 'hijas'
                    LinkedList<Casilla> lk = new LinkedList<Casilla>();
                    Casilla c;
                    // Aplica operaciones --> descubre posibles 'hijos'
                    if ((c = t.comprobarArribaBF()) != null) {
                        c.camino = t.casillaAct.camino + "U-";
                        lk.add(c);
                    }
                    if ((c = t.comprobarAbajoBF()) != null) {
                        c.camino = t.casillaAct.camino + "D-";
                        lk.add(c);
                    }
                    if ((c = t.comprobarIzdaBF()) != null) {
                        c.camino = t.casillaAct.camino + "L-";
                        lk.add(c);
                    }
                    if ((c = t.comprobarDchaBF()) != null) {
                        c.camino = t.casillaAct.camino + "R-";
                        lk.add(c);
                    }
                    // Calculo camino, tiempo y valor heuristico
                    for (Casilla c1 : lk) {
                        c1.tiempo = t.casillaAct.tiempo + Casilla.getDesnivel(t.casillaAct, c1);
                        c1.valor = h.calcHeuristica(c1, f);
                        // Agrega a q 
                        if (!q.contains(c1) && !l.contains(c1))
                            q.add(c1);
                    }
                    l.add(t.casillaAct);
                    // Ordenar lista de casillas visitadas --> 1a pos : mas favorable
                    q.sort(new Comparator<Casilla>() {
                        @Override
                        public int compare(Casilla c1, Casilla c2) {
                            return (int) (c1.valor - c2.valor);
                        }
                    });
                    // Contador iter++
                    i++;
                }
            }
            System.out.println("Iteraciones: " + i);
            System.out.println("Camino: " + t.casillaAct.camino);
            System.out.println("Coste temporal: " + t.casillaAct.tiempo);
            System.out.println("Encontrado?: " + encontrado);
            t.resetTablero();
            return encontrado;
        }
}
