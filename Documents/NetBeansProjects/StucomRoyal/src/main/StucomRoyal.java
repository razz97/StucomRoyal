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
        
        manager.genData();
        
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
