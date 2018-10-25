/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import main.PideInput;
import model.Carta;
import model.Estructura;
import model.Hechizo;
import model.Jugador;
import model.Tropa;

/**
 *
 * @author alex
 */
public class Manager {

    private static Manager instancia;
    private Set<Jugador> jugadores = new HashSet<>();
    private List<Carta> cartas = new ArrayList<>();
    private List<String> nombresCartaPorDefecto = new ArrayList<>();
    private List<String> nombresJugadorPorDefecto = new ArrayList<>();
    private Random genRandom = new Random();

    private Manager() {
        genNombresCartaPorDefecto();
        genNombresJugadorPorDefecto();
    }

    public static Manager getInstancia() {
        if (instancia == null) {
            instancia = new Manager();
        }
        return instancia;
    }

    public void genData() {
        genCartas(3, "Hechizo");
        genCartas(3, "Estructura");
        genCartas(3, "Tropa");
        genJugadores(5);
    }

    private void genNombresCartaPorDefecto() {
        nombresCartaPorDefecto.add("Caballero");
        nombresCartaPorDefecto.add("Principe");
        nombresCartaPorDefecto.add("Rey");
        nombresCartaPorDefecto.add("Reina");
        nombresCartaPorDefecto.add("Mago");
        nombresCartaPorDefecto.add("Duende");
        nombresCartaPorDefecto.add("Ogro");
        nombresCartaPorDefecto.add("Bruja");
    }

    private void genNombresJugadorPorDefecto() {
        nombresCartaPorDefecto.add("Juan");
        nombresCartaPorDefecto.add("Alex");
        nombresCartaPorDefecto.add("Victor");
        nombresCartaPorDefecto.add("Pol");
        nombresCartaPorDefecto.add("Steven");
        nombresCartaPorDefecto.add("Nico");
        nombresCartaPorDefecto.add("Javi");
        nombresCartaPorDefecto.add("Darren");
    }

    public void genCartas(int cantidad, String tipo) {
        int random;
        for (int i = 0; i < cantidad; i++) {
            random = genRandom.nextInt(nombresCartaPorDefecto.size());
            switch (tipo.toLowerCase()) {
                case "hechizo":
                    cartas.add(new Hechizo(nombresCartaPorDefecto.get(random)));
                    break;
                case "ropa":
                    cartas.add(new Tropa(nombresCartaPorDefecto.get(random)));
                    break;
                case "estructura":
                    cartas.add(new Estructura(nombresCartaPorDefecto.get(random)));
            }
        }
    }

    public void genJugadores(int cantidad) {
        int random;
        for (int i = 0; i < cantidad; i++) {
            random = genRandom.nextInt(nombresJugadorPorDefecto.size());
            jugadores.add(new Jugador(nombresJugadorPorDefecto.get(random), "0"));
        }
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    public Jugador autentificarJugador() {
        Jugador jugador = null;
        String user;
        String pass;
        boolean error;
        do {
            user = PideInput.pedirCadena("Usuario: ");
            pass = PideInput.pedirCadena("Contrasena: ");
            error = !jugadores.contains(new Jugador(user,pass));
            if (error) {
                System.err.println("Contraseña o usuario incorrectos!");
            }
        } while (error);
        for (Jugador j: jugadores) {
            if (j.getUsername().equals(user) && j.getPassword().equals(pass)) {
                return jugador;
            }
        }
        return null;
    }

}
