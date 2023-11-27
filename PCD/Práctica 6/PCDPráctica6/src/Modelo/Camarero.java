/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.CavasCongreso;
import java.time.Duration;
import java.util.Random;

/**
 *
 * @author 34667
 */
public class Camarero extends Thread {
    
    private CavasCongreso cv;
    public Camarero(CavasCongreso cv){
        this.cv = cv;
    }

    @Override
    public void run() {
        try {
            while (!this.isInterrupted()) {
                //rellena cafe y leche de 5 en 5
                cv.camarero('L');
                sleep(500);
                cv.camarero('C');
                sleep(500);
                sleep(Duration.ofSeconds(6));
            }
        } catch (InterruptedException ex) {
            System.out.println("Se ha interrumpido al camarero" + ex.getMessage());
        }
    }
}
