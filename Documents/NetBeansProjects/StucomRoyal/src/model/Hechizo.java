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
public class Hechizo extends Carta {

    private final int alcance;

    public Hechizo(String nombre) {
        super(nombre);
        this.alcance = generator.nextInt(11) + 5;
        this.ataque = ataqueBase + alcance;
        this.defensa = defensaBase + alcance;
    }

    @Override
    public String toString() {
        return super.toString() + "; Alcance: " + alcance;
    }

}
