package com.company;

import java.util.*;

public class AlgA extends Algoritmo {
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
                // Comprueba que visite casillas que ya han sido visitadas y con tiempos mayores. En dicho caso:
                //      -> actualiza (optimiza) su camino y tiempo
                if ((c = t.comprobarArribaA()) != null) {
                    if ((q.contains(c) && c.tiempo > t.casillaAct.tiempo + Casilla.getDesnivel(t.casillaAct, c)) || !q.contains(c)) {
                        c.camino = t.casillaAct.camino + "U-";
                        c.tiempo = t.casillaAct.tiempo + Casilla.getDesnivel(t.casillaAct, c);
                    }
                    if (!q.contains(c))
                        lk.add(c);
                }
                if ((c = t.comprobarAbajoA()) != null) {
                    if ((q.contains(c) && c.tiempo > t.casillaAct.tiempo + Casilla.getDesnivel(t.casillaAct, c)) || !q.contains(c)) {
                        c.camino = t.casillaAct.camino + "D-";
                        c.tiempo = t.casillaAct.tiempo + Casilla.getDesnivel(t.casillaAct, c);
                    }
                    if (!q.contains(c))
                        lk.add(c);
                }
                if ((c = t.comprobarIzdaA()) != null) {
                    if ((q.contains(c) && c.tiempo > t.casillaAct.tiempo + Casilla.getDesnivel(t.casillaAct, c)) || !q.contains(c)) {
                        c.camino = t.casillaAct.camino + "L-";
                        c.tiempo = t.casillaAct.tiempo + Casilla.getDesnivel(t.casillaAct, c);
                    }
                    if (!q.contains(c))
                        lk.add(c);
                }
                if ((c = t.comprobarDchaA()) != null) {
                    if ((q.contains(c) && c.tiempo > t.casillaAct.tiempo + Casilla.getDesnivel(t.casillaAct, c)) || !q.contains(c)) {
                        c.camino = t.casillaAct.camino + "R-";
                        c.tiempo = t.casillaAct.tiempo + Casilla.getDesnivel(t.casillaAct, c);
                    }
                    if (!q.contains(c))
                        lk.add(c);
                }
                // Comparativa con casilla final (heuristica) y calculo de h = coste_real + valor_estimado
                for (Casilla c1 : lk) {
                    c1.valor = h.calcHeuristica(c1, f);
                    c1.h = c1.valor + c1.tiempo;
                    if (!q.contains(c1) && !l.contains(c1))
                        q.add(c1);
                }
                l.add(t.casillaAct);
                // Ordenar lista de casillas visitadas --> 1a pos : mas favorable
                q.sort(new Comparator<Casilla>() {
                    @Override
                    public int compare(Casilla c1, Casilla c2) {
                        return (int) (c1.h - c2.h);
                    }
                });
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
