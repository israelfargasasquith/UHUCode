/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.util.Scanner;

/**
 *
 * @author 34667
 */
public class VistaMenu {

    private Scanner sc;
    private String opc;
    
    public VistaMenu(){
        sc = new Scanner(System.in);
    }

    public String muestraOpciones() {

        System.out.println("\n\nMenu Socios: "
                + "\n1.- Lista Socios"
                + "\n2.- Alta Socio"
                + "\n3.- Baja Socio"
                + "\n4.- Actualiza nombre Socio"
                + "\n5.- Salir"
                + "\n***********************"
                + "\nPulsa una opcion del 1 al 5, por favor: ");
        opc = sc.nextLine().substring(0,1);
        return opc;
    }
}
