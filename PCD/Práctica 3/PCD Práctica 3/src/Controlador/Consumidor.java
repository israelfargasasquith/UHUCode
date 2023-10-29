/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.PilaLenta;
import static java.lang.Thread.sleep;
import java.util.Random;

/**
 *
 * @author 34667
 */
public class Consumidor implements Runnable {

    private PilaLenta p;
    private CanvasPila cv;

    public Consumidor(PilaLenta p, CanvasPila cv) {
        this.p = p;
        this.cv = cv;
    }

    @Override
    public void run() { //va a quitar 10 numeros a la pila que comparten todos
        Random rand = new Random(System.nanoTime());
        try {
            sleep(rand.nextInt(5, 8) * 100);

        } catch (Exception ex) {
            System.out.println("Error sleep Consumidor: " + Thread.currentThread().getName());
        }
        int i = 0;
        boolean sinIntentos = false;
        while (i < 15 && !sinIntentos) {
            try {
                p.desapila();
                System.out.println("Consumidor: " + Thread.currentThread().getName() + " ha desapilado.  i = " + (i + 1) + "/15");
                cv.avisa("Consumidor: " + Thread.currentThread().getName() + " ha desapilado.  i = " + (i + 1) + "/15");
            } catch (Exception ex) {
                System.out.println("Error Consumidor: " + Thread.currentThread().getName() + " con mensaje " + ex.getMessage());
                cv.avisa("Error Consumidor: " + Thread.currentThread().getName() + " con mensaje " + ex.getMessage());
                sinIntentos = true;
            }
            i++;
            // p.muestraPila();
        }
        System.out.println("Consumidor: " + Thread.currentThread().getName() + " ya he acabado me voy");
    }
}
