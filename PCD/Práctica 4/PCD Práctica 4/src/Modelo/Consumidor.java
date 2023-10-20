/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import static java.lang.Thread.sleep;
import java.util.Random;

/**
 *
 * @author 34667
 */
public class Consumidor implements Runnable {

    private PilaLenta p;

    public Consumidor(PilaLenta p) {
        this.p = p;
    }

    @Override
    public void run() { 
        Random rand = new Random(System.nanoTime());
        try {
            sleep(rand.nextInt(1, 4) * 100);

        }catch(Exception ex){
            System.out.println("Error sleep Consumidor: "+Thread.currentThread().getName());
        }
        int i=0;
        boolean sinIntentos = false;
        while(i<15 && !sinIntentos) {
            try {
                p.desapila();
                 System.out.println("Consumidor: " + Thread.currentThread().getName() + " ha desapilado.  i = " + (i+1)+"/15");
            } catch (Exception ex) {
                System.out.println("Error Consumidor: " + Thread.currentThread().getName() + " con mensaje " + ex.getMessage());
                sinIntentos=true;
            }
            i++;
            // p.muestraPila();

        }
    }
}
