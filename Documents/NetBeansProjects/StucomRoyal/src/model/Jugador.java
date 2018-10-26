/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collections;
import java.util.Objects;

/**
 *
 * @author alex_bou
 */
public class Jugador implements Comparable<Jugador> {

    private String username;
    private String password;
    private int trofeos = 0;
    private Baraja cartas = new Baraja();

    public Jugador(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public boolean canPlay() {
        if (cartas.size() <= 3) {
            return false;
        }
        Collections.sort(cartas);        
        int minElixir = cartas.subList(0, 3).stream().mapToInt(c -> c.getElixir()).sum();
        return minElixir <= 10;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void remove(Carta carta) throws Exception {
        if (!cartas.remove(carta)) {
            throw new Exception("El jugador no tiene esta carta.");
        }
    }
    
    public void ganar() {
        trofeos++;
    }
    
    @Override
    public int compareTo(Jugador o) {
        return trofeos - o.getTrofeos();
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
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    @Override
    public String toString() {
        return "Nombre: " + username + ", Trofeos: " + trofeos;
    }
    
}
