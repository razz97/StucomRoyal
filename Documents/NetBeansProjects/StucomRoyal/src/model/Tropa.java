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
public class Tropa extends Carta {
    
    private final int fuerza;

    public Tropa() {
        this.fuerza = generator.nextInt(6) + 1;
        this.ataque = ataqueBase * fuerza;
        this.defensa = defensaBase;
    }
    
}
