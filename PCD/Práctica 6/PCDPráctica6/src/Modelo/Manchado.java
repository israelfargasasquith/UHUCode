/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.CavasCongreso;
import static java.lang.Thread.sleep;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author 34667
 */
public class Manchado implements Runnable {

 
    private Semaphore salaCafe, salaLeche, papelera, maquinaLeche, maquinaCafe;
    private CavasCongreso cv;
    private Random rand;

    public Manchado(Semaphore salaCafe, Semaphore salaLeche, Semaphore papelera, Semaphore maquinaLeche, Semaphore maquinaCafe, CavasCongreso cv) {
        this.papelera = papelera;
        this.salaCafe = salaCafe;
        this.salaLeche = salaLeche;
        this.maquinaLeche = maquinaLeche;
        this.maquinaCafe = maquinaCafe;
        this.cv = cv;

        rand = new Random(System.nanoTime());
    }

    @Override
    public void run() {
        try {

            //uno de cafe y dos de leche
            
            salaCafe.acquire();
            //quitamos uno de cafe del recurso cuando seamos los primeros en la sala
            salaCafe.release();
            salaLeche.acquire();
            //quitamos dos de leche a la maquina cuando seamos los primeros en la cola
            salaLeche.release();
            sleep(Duration.ofSeconds(rand.nextInt(1, 4)));
            papelera.acquire();
            sleep(Duration.ofSeconds(1));
            papelera.release();
        } catch (InterruptedException ex) {
            System.out.println("Ha llegado una interrupcion: " + ex.getMessage());
        }
    }
}
