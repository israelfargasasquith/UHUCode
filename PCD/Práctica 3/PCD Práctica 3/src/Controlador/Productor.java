/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.PilaLenta;
import java.util.Random;

/**
 *
 * @author 34667
 */
public class Productor extends Thread {

    private PilaLenta p;
    private CanvasPila cv;

    public Productor(PilaLenta p, CanvasPila cv) {
        this.p = p;
        this.cv = cv;
    }

    @Override
    public void run() { //va a añadir 10 numeros a la pila que comparten todos
        Random rand = new Random(System.nanoTime());
        try {
            sleep(rand.nextInt(5, 8) * 100);
        } catch (Exception ex) {
            System.out.println("Error sleep productor: " + Thread.currentThread().getName());
        }
        int num, i = 0;
        boolean sinIntentos = false;
        while (i < 15 && !sinIntentos) {
            num = rand.nextInt(0, 99);
            try {
                p.apila(num);
                System.out.println("Productor: " + Thread.currentThread().getName() + " insertado: " + num + ". i = " + (i + 1) + "/15");
                cv.avisa("Productor: " + Thread.currentThread().getName() + " insertado: " + num + ". i = " + (i + 1) + "/15");
            } catch (Exception ex) {
                System.out.println("Error productor: " + Thread.currentThread().getName() + " con mensaje " + ex.getMessage());
                cv.avisa("Error productor: " + Thread.currentThread().getName() + " con mensaje " + ex.getMessage());
                sinIntentos = true;
            }
            i++;
            // p.muestraPila();

        }
        System.out.println("Productor: " + Thread.currentThread().getName() + " ya he acabado me voy");

    }

}
