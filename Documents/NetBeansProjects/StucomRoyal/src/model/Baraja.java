/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Clase contenedora de las cartas de un jugador, extiende de ArrayList para 
 * poder ser tratada como una lista, pero con un atributo elixir.
 * Implementa Cloneable para poder ser clonada.
 * @author alex
 */
public class Baraja extends ArrayList<Carta> implements Cloneable {

    private int elixir;

    @Override
    public boolean add(Carta e) {
        elixir += e.getElixir();
        return super.add(e);
    }

    @Override
    public boolean remove(Object o) {
        elixir -= ((Carta) o).getElixir();
        return super.remove(o);
    }
    
    /**
     * Decide si esta baraja tiene mas vida que la indicada por el parametro. 
     * @param b baraja con la que comparar.
     * @return boolean 
     */
    public boolean masVidaQue(Baraja b) {
        return getVida() > b.getVida();
    }
    /**
     * Calcula el total de vida de las cartas que componen la baraja,
     * comprobando que no sea menor a 0, en cuyo caso devuelve 0.
     * @return int con el total de vida
     */
    public int getVida() {
        int vida = stream().mapToInt(c -> c.getVida() > 0 ? c.getVida() : 0).sum();
        return vida > 0 ? vida : 0;
    }

    @Override
    public Baraja clone() {
        return (Baraja) super.clone();
    }
    
    public int getElixir() {
        return elixir;
    }

    @Override
    public String toString() {
        return "Baraja (" + elixir + " elixir): \n" + this.stream()
                .map(Carta::toString)
                .collect(Collectors.joining("\n"));
    }

}
