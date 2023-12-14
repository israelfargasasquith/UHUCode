/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.CavasCongreso;
import java.time.Duration;
import java.util.concurrent.Semaphore;

/**
 *
 * @author 34667
 */
public class Camarero extends Thread {

    private CavasCongreso cv;
    private Semaphore maquinaLeche;
    private Semaphore maquinaCafe;

    public Camarero(CavasCongreso cv, Semaphore maquinaLeche, Semaphore maquinaCafe) {
        this.cv = cv;
        this.maquinaLeche = maquinaLeche;
        this.maquinaCafe = maquinaCafe;

    }

    @Override
    public void run() {
        try {
            while (!this.isInterrupted()) {
                System.out.println("Camarero: voy a rellenar cafe*******");
                //rellena cafe y leche de 5 en 5
                for (int i = 0; i < 5; i++) {
                    maquinaLeche.release();
                    maquinaCafe.release();
                }
                 cv.camarero('L');
                sleep(500);
                cv.fincamarero();

                cv.camarero('C');
                sleep(500);
                cv.fincamarero();
                sleep(Duration.ofSeconds(5));
                System.out.println("Camarero: termine de rellenar**********");

            }
        } catch (InterruptedException ex) {
            System.out.println("Se ha interrumpido al camarero" + ex.getMessage());
        }
    }
}
