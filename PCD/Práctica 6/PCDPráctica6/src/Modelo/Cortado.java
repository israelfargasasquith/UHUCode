/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.CavasCongreso;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 34667
 */
public class Cortado extends Thread {

    private Semaphore salaCafe, salaLeche, papelera, maquinaLeche, maquinaCafe;
    private CavasCongreso cv;
    private Random rand;

    public Cortado(Semaphore salaCafe, Semaphore salaLeche, Semaphore papelera, Semaphore maquinaLeche, Semaphore maquinaCafe, CavasCongreso cv) {
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

            //uno de leche y dos de cafe
            salaLeche.acquire();
            maquinaLeche.acquire();
            //quitamos uno de leche del recurso cuando seamos los primeros en la sala
            maquinaLeche.release();
            salaLeche.release();
            salaCafe.acquire();
            maquinaCafe.acquire();
            //quitamos dos de cafe a la maquina cuando seamos los primeros en la cola
            maquinaCafe.release();
            salaCafe.release();
            sleep(Duration.ofSeconds(rand.nextInt(1, 4)));
            papelera.acquire();
            sleep(Duration.ofSeconds(1));
            papelera.release();
        } catch (InterruptedException ex) {
            System.out.println("Ha llegado una interrupcion: " + ex.getMessage());
        }
    }
}
