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
public abstract class Carta implements Cloneable, Comparable<Carta> {

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

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int defender(int ataque) {
        int dano = ataque - defensa;
        if (dano > 0) {
            vida -= dano;
        } else {
            return 0;
        }
        return dano;
    }

    public String atacar(Carta c) {
        String result = nombre + " (Ataque: " + ataqueBase + ") ataca a " + c.getStatsDef();
        result += " " + c.getNombre() + " pierde " + c.defender(ataque) + " de vida ";
        result += c.getVida() > 0 ? "y se queda con " + c.getVida() + " puntos de vida!" : " y muere!";
        return result;
    }

    public String getStatsDef() {
        return nombre + " (Defensa: " + defensaBase + ", Vida: " + vida + ")";
    }

    @Override
    public String toString() {
        return "Carta tipo " + getClass().getSimpleName() + "; Nombre: " + nombre + "; Elixir: " + elixir + "; Vida: " + vida + "; Ataque: " + ataqueBase + "; Defensa: " + defensaBase;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Carta o) {
        return elixir - o.getElixir();
    }
}
