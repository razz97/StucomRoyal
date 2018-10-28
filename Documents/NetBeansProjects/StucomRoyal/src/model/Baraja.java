/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
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

    public boolean masVidaQue(Baraja b) {
        return getVida() > b.getVida();
    }
    
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
