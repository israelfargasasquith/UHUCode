/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 34667
 */
public class Productor extends Thread {

    private PilaLenta p;

    public Productor(PilaLenta p) {
        this.p = p;
    }

    @Override
    public void run() {
        Random rand = new Random(System.nanoTime());
        try {
            sleep(rand.nextInt(1, 4) * 100);
        } catch (Exception ex) {
            System.out.println("Error sleep productor: " + Thread.currentThread().getName());
        }
        int num, i=0;
        boolean sinIntentos = false;
        while(i <15 && !sinIntentos) {
            num = rand.nextInt(0, 99);
            try {
                p.apila(num);
                System.out.println("Productor: " + Thread.currentThread().getName() + " insertado: " + num + ". i = " + (i+1) + "/15");
            } catch (Exception ex) {
                System.out.println("Error productor: " + Thread.currentThread().getName() + " con mensaje " + ex.getMessage());
                sinIntentos=true;
            }
            i++;
            // p.muestraPila();

        }

    }

}
