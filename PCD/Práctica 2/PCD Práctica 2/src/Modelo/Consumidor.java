/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

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
    public void run() { //va a quitar 10 numeros a la pila que comparten todos
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("El consumidor con nombre: " + Thread.currentThread().getName() + " va desapilar en su iteracion numero: i = " + i);
                p.desapila();
            } catch (Exception ex) {
                System.out.println("Error con el consumidor de nombre: " + Thread.currentThread().getName() + " con mensaje " + ex.getMessage());
            }
            p.muestraPila();

        }
    }
}
