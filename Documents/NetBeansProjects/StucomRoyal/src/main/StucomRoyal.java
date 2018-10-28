/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.Manager;
import java.util.Collections;
import java.util.List;
import model.Baraja;
import model.Carta;
import model.Jugador;

/**
 *
 * @author alex
 */
public class StucomRoyal {

    private static final Manager MANAGER = Manager.getInstancia();
    private static final int MAX_ELIXIR = 10;

    public static void main(String[] args) {
        // Gonzalo esto es para que veas los usuarios creados con sus contraseñas.
        MANAGER.getJugadores().stream()
                .map(j -> j + "; Contrasena: " + j.getPassword())
                .forEach(System.out::println);
        int opcion;
        do {
            System.out.println("1. Ver perfil jugador\n"
                    + "2. Conseguir cartas \n"
                    + "3. Batalla \n"
                    + "4. Ranking.\n"
                    + "0. Salir.");
            opcion = PideInput.pedirEntero("Selecciona una opcion: ", 0, 4);
            switch (opcion) {
                case 1:
                    Jugador jugador = MANAGER.authJugador(j -> true, "Datos incorrectos");
                    System.out.println(jugador + "\n" + jugador.getCartas());
                    break;
                case 2:
                    conseguirCartas();
                    break;
                case 3:
                    empezarBatalla();
                    break;
                case 4:
                    mostrarRanking();
                    break;
                case 0:
                    System.out.println("Hasta pronto!");
            }
        } while (opcion != 0);
    }

    private static void conseguirCartas() {
        List<Carta> cartas = MANAGER.getCartas();
        Jugador jugador = MANAGER.authJugador(j -> true, "Datos incorrectos");
        int option;
        do {
            option = PideInput.pedirIndice("Selecciona una carta: ", cartas, true);
            if (option != -1) {
                try {
                    jugador.add(cartas.get(option));
                    cartas.remove(option);
                    if (cartas.size() < 3) MANAGER.genCartas(8);
                    System.out.println("Carta anadida correctamente.");
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                    option = -1;
                }
            }
        } while (option != -1);
    }

    private static void empezarBatalla() {
        if (MANAGER.dosJugadoresPuedenJugar()) {
            System.out.println("Login primer usuario");
            Jugador jugador1 = MANAGER.authJugador(Jugador::canPlay,"Datos incorrectos o el jugador no puede jugar");
            Baraja baraja1 = getCartasLegales(jugador1);
            System.out.println("Login segundo usuario");
            Jugador jugador2 = MANAGER.authJugador(Jugador::canPlay,"Datos incorrectos o el jugador no puede jugar");
            Baraja baraja2 = getCartasLegales(jugador2);
            if (jugador1.isJugadorPrimero(jugador2)) {
                System.out.println(jugador1.getUsername() + " tira primero.");
                ejecutarBatalla(baraja1, baraja2);
            } else {
                System.out.println(jugador2.getUsername() + " tira primero.");
                ejecutarBatalla(baraja2, baraja1);
            }
            if (baraja1.masVidaQue(baraja2)) {
                System.out.println(jugador1.getUsername() + " ha ganado! (" 
                        + baraja1.getVida() +"-" + baraja2.getVida() + ")");
                jugador1.ganar();
            } else {
                System.out.println(jugador2.getUsername() + " ha ganado! (" 
                        + baraja2.getVida() +"," + baraja1.getVida() + ")");
                jugador2.ganar();
            }
        } else {
            System.err.println("No hay dos jugadores que puedan jugar.");
        }

    }

    private static void ejecutarBatalla(Baraja primero, Baraja segundo) {
        for (int i = 0; i < 3; i++) 
            System.out.println(primero.get(i).atacar(segundo.get(i)));
        
        System.out.println("Turno del segundo jugador.");
        for (int i = 0; i < 3; i++) 
            System.out.println(segundo.get(i).atacar(primero.get(i)));
    }

    private static Baraja getCartasLegales(Jugador jugador) {
        Baraja barajaClon = jugador.getCartas().clone();
        Baraja barajaJugable = new Baraja();
        Carta cartaSeleccionada;
        int indice, elixir = 0;
        do {
            indice = PideInput.pedirIndice("Selecciona una carta (elixir actual: " + elixir + "/10)", barajaClon, false);
            cartaSeleccionada = barajaClon.get(indice);
            if (cartaSeleccionada.getElixir() + elixir <= MAX_ELIXIR) {
                barajaJugable.add(cartaSeleccionada);
                barajaClon.remove(cartaSeleccionada);
                elixir = barajaJugable.getElixir();
            } else {
                System.err.println("No puedes escoger esta carta, maximo 10 de elixir permitido");
            }
        } while (barajaJugable.size() < 3);
        return barajaJugable;
    }

    private static void mostrarRanking() {
        List<Jugador> jugadores = MANAGER.getJugadores();
        Collections.sort(jugadores);
        for (int i = 0; i < jugadores.size(); i++) {
            System.out.println((i + 1) + ". " + jugadores.get(i));
        }
        System.out.println("------------------------");
    }

}
