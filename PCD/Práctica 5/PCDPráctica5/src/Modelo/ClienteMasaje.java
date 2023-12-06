/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.Centro;
import java.util.Random;


/**
 *
 * @author 34667
 */
public class ClienteMasaje extends Thread {

    private Centro centro;
    private Random rand;
    private String id;

    public ClienteMasaje(Centro centro, String id) {
        this.centro = centro;
        this.id = id;
        rand = new Random(System.nanoTime());
    }

    @Override
    public void run() {
        try {
            System.out.println("Soy hilo " + id + " de la clase masaje y acabo de nacer");
            centro.entraMasaje(id); //Hay que cambiarlo ya que puede entrar en el fisio si este esta libre
            Thread.currentThread().sleep(rand.nextInt(2, 5) * 1000);
            centro.saleMasaje(id);
            Thread.currentThread().sleep(rand.nextInt(2, 5) * 1000);
            centro.termina(id);
            System.out.println("Soy hilo " + id + "de la clase masaje y he acabado");
        } catch (InterruptedException ex) {
            System.out.println("Error con los hilos:" + ex.getMessage());
        }
    }
}
