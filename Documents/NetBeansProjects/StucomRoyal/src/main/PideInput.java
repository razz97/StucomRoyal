/*
 * Funciones para solicitar datos al usuario (por teclado)
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 *
 * @author mfontana
 */
public class PideInput {
    /**
     * Pide una cadena al usuario hasta que no este vacia.
     * @param texto texto a mostrar al usuario.s
     * @return string introducida por el usuario.
     */
    public static String pedirCadena(String texto) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cadena = "";
        do {
            try {
                System.out.println(texto);
                cadena = br.readLine();
                if (cadena.equals("")) {
                    System.out.println("No se puede dejar el campo en blanco.");
                }
            } catch (IOException ex) {
                System.out.println("Error de entrada / salida.");
            }
        } while (cadena.equals(""));
        return cadena;
    }

    /**
     * Pide un entero al usuario hasta que lo introduce correctamente.
     * @param texto enunciado a mostrar al usuario.
     * @return entero introducido.
     */
    public static int pedirEntero(String texto) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        boolean error;
        do {
            try {
                System.out.println(texto);
                num = Integer.parseInt(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Error de entrada / salida.");
                error = true;
            } catch (NumberFormatException ex) {
                System.out.println("Debes introducir un número entero.");
                error = true;
            }
        } while (error);
        return num;
    }
    /**
     * Pide un entero al usuario hasta que lo introduce correctamente, 
     * comprueba que este entre un minimo y un maximo pasados como argumentos.
     * @param texto enunciado a mostrar al usuario.
     * @param min minimo del entero a introducir.
     * @param max maximo del entero a introducir.
     * @return 
     */
    public static int pedirEntero(String texto, int min, int max) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        boolean error;
        do {
            try {
                System.out.println(texto);
                num = Integer.parseInt(br.readLine());
                error = num > max || num < min;
                if (error) {
                    System.out.println("El numero debe estar entre " + min + " y " + max);
                }
            } catch (IOException ex) {
                System.out.println("Error de entrada / salida.");
                error = true;
            } catch (NumberFormatException ex) {
                System.out.println("Debes introducir un número entero.");
                error = true;
            }
        } while (error);
        return num;
    }

    /**
     * A partir de una lista devuelve el indice deseado por el usuario.
     * @param pregunta enunciado a mostrar al usuario.
     * @param lista lista de elementos a escoger.
     * @param salirConZero boolean de si se puede salir del menu con 0.
     * @return indice introducido por el usuario.
     */
    public static int pedirIndice(String pregunta,List lista, boolean salirConZero) {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
        if (salirConZero) {
            System.out.println("0. Salir.");
        }
        return pedirEntero(pregunta, salirConZero ? 0 : 1, lista.size()) - 1;
    }
    
}
