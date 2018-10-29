/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collections;
import java.util.Objects;
import java.util.Random;

/**
 * Esta clase sirve para representar a un jugador del juego.
 * Implementa comparable para poder hacer el ranking por trofeos.
 * @author alex_bou
 */
public class Jugador implements Comparable<Jugador> {

    private final String username;
    private final String password;
    private int trofeos = 0;
    private final Baraja cartas = new Baraja();

    public Jugador(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    /**
     * Determina si el jugador puede jugar a partir de la baraja de cartas que tiene.
     * - Minimo tres cartas 
     * - La suma de elixir de las tres cartas con menos elixir debe ser menor o igual a 10.
     * @return 
     */
    public boolean canPlay() {
        if (cartas.size() < 3) {
            return false;
        }
        Collections.sort(cartas);
        int minElixir = cartas.subList(0, 3).stream().mapToInt(Carta::getElixir).sum();
        return minElixir <= 10;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getTrofeos() {
        return trofeos;
    }

    public Baraja getCartas() {
        return cartas;
    }
    
    /**
     * Añade una carta a la baraja si el jugador tiene menos de 6.
     * @param carta
     * @throws Exception 
     */
    public void add(Carta carta) throws Exception {
        if (cartas.size() >= 6) {
            throw new Exception("El jugador ya tiene 6 cartas.");
        }
        cartas.add(carta);
    }
    
    /**
     * Suma los trofeos por ganar una batalla.
     */
    public void ganar() {
        trofeos += 5;
    }
    /**
     * Suma los trofeos por perder una batalla.
     */
    public void perder() {
        trofeos++;
    }
    /**
     * Comprueba que jugador debe empezar la batalla.
     * @param j jugador con quien comparar.
     * @return true si este objeto debe empezar, false si es el otro.
     */
    public boolean isJugadorPrimero(Jugador j) {
        return compareTo(j) > 0;
    }

    @Override
    public int compareTo(Jugador o) {
        if (trofeos == o.getTrofeos()) {
            return (new Random()).nextInt();
        }
        return o.getTrofeos() - trofeos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.username);
        hash = 29 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jugador other = (Jugador) obj;
        if (!this.username.equalsIgnoreCase(other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    @Override
    public String toString() {
        return "Nombre: " + username + ", Trofeos: " + trofeos;
    }

}
