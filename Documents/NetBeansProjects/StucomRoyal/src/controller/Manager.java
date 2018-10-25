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
    private Random genRandom = new Random();

    private Manager() {
        genNombresCartaPorDefecto();
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
        genJugadoresPorDefecto("0");
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

    private void genJugadoresPorDefecto(String contrasena) {
        jugadores.add(new Jugador("Juan", contrasena));
        jugadores.add(new Jugador("Alex", contrasena));
        jugadores.add(new Jugador("Victor", contrasena));
        jugadores.add(new Jugador("Pol", contrasena));
        jugadores.add(new Jugador("Steven", contrasena));
        jugadores.add(new Jugador("Nico", contrasena));
        jugadores.add(new Jugador("Javi", contrasena));
        jugadores.add(new Jugador("Darren", contrasena));
    }

    public void genCartas(int cantidad, String tipo) {
        int random;
        for (int i = 0; i < cantidad; i++) {
            random = genRandom.nextInt(nombresCartaPorDefecto.size());
            switch (tipo.toLowerCase()) {
                case "hechizo":
                    cartas.add(new Hechizo(nombresCartaPorDefecto.get(random)));
                    break;
                case "tropa":
                    cartas.add(new Tropa(nombresCartaPorDefecto.get(random)));
                    break;
                case "estructura":
                    cartas.add(new Estructura(nombresCartaPorDefecto.get(random)));
            }
        }
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    public Jugador autentificarJugador() {
        Jugador jugador;
        String user;
        String pass;
        boolean encontrado = false;
        do {
            user = PideInput.pedirCadena("Usuario: ");
            pass = PideInput.pedirCadena("Contrasena: ");
            jugador = new Jugador(user, pass);
            for (Jugador j : jugadores) {
                if (jugador.equals(j)) {
                    jugador = j;
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.err.println("Contraseña o usuario incorrectos!");
            }
        } while (!encontrado);
        return jugador;
    }

}
