/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author alex_bou
 */
public class Estructura extends Carta {

    private final int escudo;

    public Estructura(String nombre) {
        super(nombre);
        this.escudo = generator.nextInt(6) + 1;
        this.ataque = ataqueBase;
        this.defensa = defensaBase * escudo;
    }

    @Override
    public String toString() {
        return super.toString() + "; Escudo: " + escudo;
    }
    
}
