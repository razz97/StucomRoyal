/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Carta;
import model.Jugador;

/**
 *
 * @author alex
 */
public class Manager {

    private static Manager instancia;
    private static Set<Jugador> jugadores = new HashSet<>();
    private static List<Carta> cartas = new ArrayList<>();
    private static List<String> nombresCartaPorDefecto = new ArrayList<>();
    private static List<String> nombresJugadorPorDefecto = new ArrayList<>();

    private Manager() {
        genNombresCartaPorDefecto();
    }

    public static Manager getInstancia() {
        if (instancia == null) {
            instancia = new Manager();
        }
        return instancia;
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

    public static void genCartas(int cantidad, String clase) throws Exception {

        Random genRandom = new Random();
        int random;
        for (int i = 0; i < cantidad; i++) {
            random = genRandom.nextInt(nombresCartaPorDefecto.size());
            Carta c = (Carta) Class.forName("model." + clase).newInstance();
            c.setNombre(nombresCartaPorDefecto.get(random));
            cartas.add(c);
        }
        
    }
    
    public List<Carta> getCartas() {
        return cartas;
    }

}
