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
    public boolean add(Carta element) {
        elixir += element.getElixir();
        return super.add(element); 
    }
    
    public boolean remove(Carta element) {
        elixir -= element.getElixir();
        return super.remove(element);
    }
    
    @Override
    public Baraja clone() {
        return (Baraja)super.clone();
    }
    
    public int getElixir() {
        return elixir;
    }

}
