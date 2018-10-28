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
 *
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

    public void add(Carta carta) throws Exception {
        if (cartas.size() >= 6) {
            throw new Exception("El jugador ya tiene 6 cartas.");
        }
        cartas.add(carta);
    }

    public void ganar() {
        trofeos += 5;
    }
    
    public void perder() {
        trofeos++;
    }
    
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
