/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex_bou
 */
public class Jugador {

    private String username;
    private String password;
    private int trofeos = 0;
    private List<Carta> cartas = new ArrayList<Carta>();

    public Jugador(String username, String password) {
        this.username = username;
        this.password = password;
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

    public List<Carta> getCartas() {
        return cartas;
    }

    public void add(Carta carta) throws Exception {
        if (cartas.size() >= 6) {
            throw new Exception("El jugador ya tiene 6 cartas.");
        }
        if (cartas.contains(carta)) {
            throw new Exception("El jugador ya tiene esta carta.");
        }
        cartas.add(carta);
    }

    public void remove(Carta carta) throws Exception {
        if (!cartas.contains(carta)) {
            throw new Exception("El jugador no tiene esta carta.");
        }
        cartas.remove(carta);
    }
    
    public void ganar() {
        trofeos++;
    }

}
