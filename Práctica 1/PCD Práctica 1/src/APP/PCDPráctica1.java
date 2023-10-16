/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package APP;

import Modelo.Pila;
import java.util.Random;

/**
 *
 * @author israel
 */
public class PCDPráctica1 {

    /**zxsb
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random rand = new Random(System.currentTimeMillis());
        Pila p = new Pila(10);
        int num = 0;
        for (int i = 0; i < 10; i++) {
            num = rand.nextInt(0, 99);
            if (num % 2 == 0) {
                try {
                    System.out.println("Se va a intentar apilar ->" + num + " en la vuelta i = " + i);
                    p.apila(num);
                    System.out.println("");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    System.out.println("Se va a desapilar porque el num ->" + num + " es impar, en la vuelta i = " + i);
                    p.desapila();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            p.verPila();
        }

    }

}
