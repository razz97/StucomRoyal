/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;

/**
 *
 * @author alex_bou
 */
public abstract class Carta {

    private String nombre;
    protected final int ataqueBase;
    protected final int defensaBase;
    protected int elixir;
    protected int vida;
    protected int ataque;
    protected int defensa;
    protected Random generator = new Random();

    public Carta(String nombre) {
        this.nombre = nombre;
        this.ataqueBase = generator.nextInt(51) + 1;
        this.defensaBase = generator.nextInt(51) + 1;
        this.elixir = generator.nextInt(6) + 1;
        this.vida = generator.nextInt(101) + 1;
    }

    public Carta() {
        this.ataqueBase = generator.nextInt(51) + 1;
        this.defensaBase = generator.nextInt(51) + 1;
        this.elixir = generator.nextInt(6) + 1;
        this.vida = generator.nextInt(101) + 1;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAtaqueBase() {
        return ataqueBase;
    }

    public int getDefensaBase() {
        return defensaBase;
    }

    public int getElixir() {
        return elixir;
    }

    public int getVida() {
        return vida;
    }

    public int atacar() {
        elixir--;
        return ataque;
    }

    public int defender(int ataque) {
        vida -= ataque;
        return defensa;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + nombre;
    }
    

}
