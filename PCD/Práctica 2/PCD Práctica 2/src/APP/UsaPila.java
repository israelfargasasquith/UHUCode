/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package APP;

import Modelo.Consumidor;
import Modelo.PilaLenta;
import Modelo.Productor;
import java.util.Random;
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

        PilaLenta p = new PilaLenta(10);

        Productor p1 = new Productor(p);
        Productor p2 = new Productor(p);
        Consumidor t1 = new Consumidor(p);
        Consumidor t2 = new Consumidor(p);
        Thread c1 = new Thread(t1);
        Thread c2 = new Thread(t2);
       try{
        p1.start();
        p2.start();
        c1.start();
        c2.start();
        p1.join();
        p2.join();
        c1.join();
        c2.join();
       }catch (Exception ex){
           System.out.println("Error al lanzar los hilos concurrentes con mensaje: "+ex.getMessage());
       }
        
       /* try {
            p1.start();
            p1.join();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        p.muestraPila();
        try {
            c1.start();
            c1.join();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        p.muestraPila();*/ //Aqui se ve que secuencial funciona

    }

}
