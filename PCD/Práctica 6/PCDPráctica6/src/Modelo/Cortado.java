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
    private int id;

    public Cortado(Semaphore salaCafe, Semaphore salaLeche, Semaphore papelera, Semaphore maquinaLeche, Semaphore maquinaCafe, CavasCongreso cv, int id) {
        this.papelera = papelera;
        this.salaCafe = salaCafe;
        this.salaLeche = salaLeche;
        this.maquinaLeche = maquinaLeche;
        this.maquinaCafe = maquinaCafe;
        this.cv = cv;
        this.id = id;

        rand = new Random(System.nanoTime());
    }

    @Override
    public void run() {
        try {

            int leche = 0;
            int cafe = 0;
            char tipo = 'C';
            //uno de leche y dos de cafe
            System.out.println("Cortado " + id + " espero para entrar en sala de leche");
            cv.encolaleche(id, tipo, leche, cafe);

            salaLeche.acquire();
            cv.fincolaleche(id, tipo, leche, cafe);
            sleep(500);
            cv.ensalaleche(id, tipo, leche, cafe);
            System.out.println("Cortado " + id + " espero para maquina leche (0Le 0Ca)");

            maquinaLeche.acquire();
            leche++;

            System.out.println("Cortado " + id + " salgo sala leche (1Le 0Ca)");
            cv.finsalaleche(id, tipo, leche, cafe);
            sleep(500);
            salaLeche.release();

            System.out.println("Cortado " + id + " espero para sala cafe (1Le 0Ca)");
            cv.encolacafe(id, tipo, leche, cafe);

            salaCafe.acquire();
            cv.fincolacafe(id, tipo, leche, cafe);
            sleep(500);
            cv.ensalacafe(id, tipo, leche, cafe);

            System.out.println("Cortado " + id + " espero para maquina cafe (1Le 0Ca)");
            maquinaCafe.acquire();
            cafe++;

            System.out.println("Cortado " + id + " espero para maquina cafe tengo (1Le 1Ca)");
            maquinaCafe.acquire();
            cafe++;
            System.out.println("Cortado " + id + " cafe servido (1Le 2Ca)");
            cv.finsalacafe(id, tipo, leche, cafe);

            salaCafe.release();
            sleep(500);
            cv.ensalon(id, tipo, leche, cafe);
            sleep(Duration.ofSeconds((long) rand.nextInt(1, 4))); //tomamos cafe durante 1 a 3 segundos
            cv.finsalon(id, tipo, leche, cafe);
            sleep(500);

            System.out.println("Cortado " + id + " quiero papelera");
            cv.encolapapelera(id, tipo, leche, cafe);
            papelera.acquire();
            sleep(500);
            cv.fincolapapelera(id, tipo, leche, cafe);

            cv.enpapelera(id, tipo, leche, cafe);
            sleep(Duration.ofSeconds((long) 1));
            cv.finpapelera(id, tipo, leche, cafe);
            sleep(500);

            cv.ensalon(id, tipo, leche, cafe);
            papelera.release();
            System.out.println("Cortado " + id + " acabe");
        } catch (InterruptedException ex) {
            System.out.println("Ha llegado una interrupcion: " + ex.getMessage());
        }
    }
}
