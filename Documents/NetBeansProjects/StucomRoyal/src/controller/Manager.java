/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import main.PideInput;
import model.Carta;
import model.Estructura;
import model.Hechizo;
import model.Jugador;
import model.Tropa;

/**
 * Esta clase sirve como controlador de la aplicacion, es la encargada de 
 * gestionar y enviar los datos a la vista (StucomRoyal.java).
 * @author alex
 */
public class Manager {

    private static Manager instancia;
    private List<Carta> cartas = new ArrayList<>();
    private Random genRandom = new Random();

    private List<Jugador> jugadores = Arrays.asList(
            new Jugador("Laura", "0"),
            new Jugador("Alex", "0"),
            new Jugador("Victor", "0")
    );

    private List<String> nombresCarta = Arrays.asList(
            "Caballero", "Principe", "Rey", "Reina",
            "Mago", "Duende", "Ogro", "Bruja"
    );
    
    /**
     * Constructor privado para que solo se pueda llamar desde getInstancia.
     * Genera 10 cartas y añade unas cuantas a los jugadores.
     */
    private Manager() {
        this.genCartas(10);
        for (int i = 0; i < 3; i++) {
            try {
                jugadores.get(0).add(cartas.get(i));
                jugadores.get(1).add(cartas.get(i + 3));
                jugadores.get(2).add(cartas.get(i + 6));
            } catch (Exception ex) {
            }
        }
    }
    
    /**
     * Dado que solo nos interesa tener un manager, este es el metodo a usar 
     * para construirlo. Devuelve la instancia del Manager si existe y sino lo crea.
     * @return instancia de Manager
     */
    public static Manager getInstancia() {
        if (instancia == null) {
            instancia = new Manager();
        }
        return instancia;
    }

    public List<Carta> getCartas() {
        return cartas;
    }
    
    public List<Jugador> getJugadores() {
        return jugadores;
    }
    
    /**
     * Genera cartas de tipo aleatorio y las almacena en el manager,
     * el parametro indica la cantidad de cartas a generar.
     * @param cantidad de cartas.
     */
    public void genCartas(int cantidad) {
        int random;
        int tipo;
        for (int i = 0; i < cantidad; i++) {
            random = genRandom.nextInt(nombresCarta.size());
            tipo = genRandom.nextInt(3);
            switch (tipo) {
                case 0:
                    cartas.add(new Hechizo(nombresCarta.get(random)));
                    break;
                case 1:
                    cartas.add(new Tropa(nombresCarta.get(random)));
                    break;
                case 2:
                    cartas.add(new Estructura(nombresCarta.get(random)));
                    break;
            }
        }
    }
    /**
     * Pide al usuario un nombre de usuario y una contraseña a partir del cual:
     * - Comprueba que usuario y contraseña sean correctos
     * - Comprueba que cumpla una condicion (predicate que indica el primer parametro)
     * La funcion sigue pidiendo los datos hasta que se cumplan las dos condiciones.
     * Cada vez que no se cumplan las condiciones se mostrara el mensaje de error del 
     * segundo parametro.
     * @param predicate
     * @param error
     * @return Jugador autentificado
     */
    public Jugador authJugador(Predicate<Jugador> predicate, String error) {
        Jugador jugador;
        do {
            Jugador temp = new Jugador(PideInput.pedirCadena("Usuario: "), PideInput.pedirCadena("Contrasena: "));
            jugador = jugadores.stream()
                    .filter(j -> j.equals(temp))
                    .filter(predicate)
                    .findFirst().orElse(null);
            if (jugador == null) {
                System.err.println(error);
            }
        } while (jugador == null);
        return jugador;
    }

    public boolean dosJugadoresPuedenJugar() {
        return jugadores.stream()
                .filter(j -> j.canPlay())
                .count() >= 2;
    }

}
