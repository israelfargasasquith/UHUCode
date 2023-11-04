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
public class VistaLogin {
    private Scanner lector = new Scanner(System.in);
    
    public String mensajeBienvenida(){
        System.out.println("****** Bienvenido al gestor del Gym Siempre en Forma ******"
                + "Elija con que servidor quiere trabajar:"
                + "1.- MariaDB"
                + "2.- Oracle");
        return lector.next();
    }
    
    public void mensajeConsola(String mensaje){
        System.out.println(mensaje);
    }
}
