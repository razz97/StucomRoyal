/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.Manager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import model.Baraja;
import model.Carta;
import model.Jugador;

/**
 *
 * @author alex
 */
public class StucomRoyal {

    private static Manager manager = Manager.getInstancia();
    private static final int MAX_ELIXIR = 10; 

    public static void main(String[] args) {

        manager.genData();

        manager.getJugadores().forEach(x -> System.out.println(x + "; Contrasena: " + x.getPassword()));

        int opcion = -1;

        do {
            System.out.println("1. Conseguir cartas \n"
                    + "2. Batalla \n"
                    + "3. Ranking.\n"
                    + "0. Salir.");
            opcion = PideInput.pedirEntero("Selecciona una opcion: ", 0, 3);
            switch (opcion) {
                case 1:
                    conseguirCartas();
                    break;
                case 2:
                    empezarBatalla();
                    break;
                case 3:
                    mostrarRanking();
                    break;
                case 0:
                    System.out.println("Hasta pronto!");
                    break;
            }
        } while (opcion != 0);
    }

    private static void conseguirCartas() {
        List<Carta> cartas = manager.getCartas();
        Jugador jugador = manager.autentificarJugador();
        int option;
        do {
            option = PideInput.pedirIndice("Selecciona una carta: ", cartas, true);
            if (option != -1) {
                try {
                    jugador.add(cartas.get(option));
                    cartas.remove(option);
                    System.out.println("Carta anadida correctamente.");
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                    option = -1;
                }
            }
        } while (option != -1);
    }

    private static void empezarBatalla() {
        if (manager.dosJugadoresPuedenJugar()) {
            System.out.println("Login primer usuario");
            Jugador jugador1 = manager.autentificarJugadorTresCartas();
            Baraja baraja1 = getCartasLegales(jugador1);
//            System.out.println("Login segundo usuario");
//            Jugador jugador2 = manager.autentificarJugadorTresCartas();
//            Baraja baraja2 = getCartasLegales(jugador2);
            
        } else {
            System.err.println("No hay dos jugadores con al menos tres cartas.");
        }

    }
    
    private static Baraja getCartasLegales(Jugador jugador) {
        Baraja barajaClon = jugador.getCartas().clone();
        Baraja barajaJugable = new Baraja();
        Carta cartaSeleccionada;
        int indice;
        int elixir = 0;
        do {
            indice = PideInput.pedirIndice("Selecciona una carta", barajaClon, false);
            cartaSeleccionada = barajaClon.get(indice);
            if (cartaSeleccionada.getElixir() + elixir <= 10) {
                barajaJugable.add(cartaSeleccionada);
                barajaClon.remove(cartaSeleccionada);
                elixir = barajaJugable.getElixir();
            } else {
                System.err.println("No puedes escoger esta carta, maximo 10 de elixir permitido");
            }
        } while(barajaJugable.size() < 3);
        return barajaJugable;
    }

    private static void mostrarRanking() {
        List<Jugador> jugadores = manager.getJugadores();
        Collections.sort(jugadores);
        for (int i = 0; i < jugadores.size(); i++) {
            System.out.println((i + 1) + ". " + jugadores.get(i));
        }
        System.out.println("------------------------");
    }

}
