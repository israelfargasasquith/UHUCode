/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controlador;

import Modelo.Consumidor;
import Modelo.PilaLenta;
import Modelo.Productor;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 34667
 */
public class UsaPila {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PilaLenta p = new PilaLenta(5);

        Productor p1 = new Productor(p);
        Productor p2 = new Productor(p);
        Productor p3 = new Productor(p);
        Productor p4 = new Productor(p);
        
        Consumidor t1 = new Consumidor(p);
        Thread c1 = new Thread(t1);
        try {
            p1.start();
            p2.start();
            p3.start();
            p4.start();
            c1.start();
            c1.join();
        } catch (Exception ex) {
            System.out.println("Error al lanzar los hilos concurrentes con mensaje: " + ex.getMessage());
        }
        try {
            sleep(400);

        } catch (Exception ex) {
            System.out.println("Error con el sleep de la main");
        }
        try{
        for (int i = 0; i <3; i++) {
            synchronized (p) {
                p.notifyAll();
            }
            sleep(400);
        }
        
        p1.join();
        p2.join();
        p3.join();
        p4.join();
        }catch(Exception ex){
            System.out.println("Error en el sleep del notifiAll de la main o en los joins "+ ex.getMessage());
        }
        p.muestraPila();

    }
}
