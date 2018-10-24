/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.Manager;

/**
 *
 * @author alex
 */
public class StucomRoyal {
    
    private static Manager manager = Manager.getInstancia();

    public static void main(String[] args) {
        try {
            manager.genCartas(3, "Hechizo");
            manager.genCartas(3, "Estructura");
            manager.genCartas(3, "Tropa");
        } catch (Exception ex) {
            System.err.println("No existe este tipo de cartas!");
        }
        manager.getCartas().forEach(x -> System.out.println(x));
        int opcion = -1;
        do {
            System.out.println("1. Conseguir cartas \n"
                    + "2. Batalla \n"
                    + "3. Ranking.");
            PideInput.pedirEntero("Selecciona una opcion: ",0,3);
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
            }
        } while (opcion != 0);
    }

    private static void conseguirCartas() {
        
    }

    private static void empezarBatalla() {
        
    }

    private static void mostrarRanking() {
        
    }

}
