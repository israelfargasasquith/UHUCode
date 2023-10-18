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

    public Productor(PilaLenta p) {
        this.p = p;
    }

    @Override
    public void run() { //va a añadir 10 numeros a la pila que comparten todos
        Random rand = new Random(System.currentTimeMillis());
        int num;
        for (int i = 0; i < 10; i++) {
            num = rand.nextInt(0,99);
            try {
                System.out.println("El productor con nombre: "+Thread.currentThread().getName()+" va a insertar el num: "+num+" en su iteracion"
                        + "numero: i = "+i);
                p.apila(num);
            } catch (Exception ex) {
                System.out.println("Error con el productor de nombre: "+Thread.currentThread().getName()+" con mensaje "+ex.getMessage());
            }

        }
    }

}
