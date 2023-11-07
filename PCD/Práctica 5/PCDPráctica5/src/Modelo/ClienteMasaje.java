/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.Centro;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 34667
 */
public class ClienteMasaje extends Thread {

    private Centro centro;
    private Random rand;

    public ClienteMasaje(Centro centro) {
        this.centro = centro;
        rand = new Random(System.nanoTime());
    }

    @Override
    public void run() {
        try {
            centro.entraMasaje(); //Hay que cambiarlo ya que puede entrar en el fisio si este esta libre
            Thread.currentThread().sleep(rand.nextInt(2, 5) * 1000);
            centro.saleMasaje();
            Thread.currentThread().sleep(rand.nextInt(2, 5) * 1000);
            centro.termina();
        } catch (InterruptedException ex) {
            System.out.println("Error con los hilos:" + ex.getMessage());
        }
    }
}
