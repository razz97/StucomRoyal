/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

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
        elixir -= ((Carta)o).getElixir();
        return super.remove(o); 
    }
    
    
    
    public boolean masVidaque(Baraja b) {
        int miVida = this.stream().mapToInt(c -> c.getVida()).sum();
        int suVida = b.stream().mapToInt(c -> c.getVida()).sum();
        return miVida > suVida;
    }
    
    @Override
    public Baraja clone() {
        return (Baraja)super.clone();
    }
    
    public int getElixir() {
        return elixir;
    }

}
