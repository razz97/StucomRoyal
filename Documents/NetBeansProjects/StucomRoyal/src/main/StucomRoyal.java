/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.Manager;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Carta;
import model.Jugador;

/**
 *
 * @author alex
 */
public class StucomRoyal {

    private static Manager manager = Manager.getInstancia();

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

    }

    private static void mostrarRanking() {

    }

}
