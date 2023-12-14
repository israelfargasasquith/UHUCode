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
    private int id;

    public Manchado(Semaphore salaCafe, Semaphore salaLeche, Semaphore papelera, Semaphore maquinaLeche, Semaphore maquinaCafe, CavasCongreso cv, int id) {
        this.papelera = papelera;
        this.salaCafe = salaCafe;
        this.salaLeche = salaLeche;
        this.maquinaLeche = maquinaLeche;
        this.maquinaCafe = maquinaCafe;
        this.id = id;
        this.cv = cv;

        rand = new Random(System.nanoTime());
    }

    @Override
    public void run() {
        try {

            //uno de cafe y dos de leche
            int cafe = 0;
            int leche = 0;
            char tipo = 'M';

            cv.encolacafe(id, 'M', leche, cafe);
            System.out.println("Manchado " + id + " espero para entrar en sala de cafe");

            salaCafe.acquire();
            cv.fincolacafe(id, 'M', leche, cafe);
            sleep(500);
            cv.ensalacafe(id, tipo, leche, cafe);
            System.out.println("Manchado " + id + " espero maquina cafe (0Ca 0Le)");

            maquinaCafe.acquire();
            cafe++;
            System.out.println("Manchado " + id + " salgo de la sala (1Ca 0Le)");

            cv.finsalacafe(id, tipo, leche, cafe);
            sleep(500);
            salaCafe.release();

            System.out.println("Manchado " + id + " quier sala leche (1Ca 0Le)");
            cv.encolaleche(id, tipo, leche, cafe);

            salaLeche.acquire();
            cv.fincolaleche(id, tipo, leche, cafe);
            sleep(500);
            cv.ensalaleche(id, tipo, leche, cafe);

            System.out.println("Manchado " + id + " quiero leche (1Ca 0Le)");
            maquinaLeche.acquire();
            leche++;

            System.out.println("Manchado " + id + " (1Ca 1Le)");
            maquinaLeche.acquire();
            leche++;

            System.out.println("Manchado " + id + " tengo el cafe servido (1Ca 2Le)");
            cv.finsalaleche(id, tipo, leche, cafe);
            sleep(500);
            salaLeche.release();

            cv.ensalon(id, tipo, leche, cafe);
            sleep(Duration.ofSeconds((long)rand.nextInt(1, 4))); //tomamos cafe durante 1-3 segundos
            cv.finsalon(id, tipo, leche, cafe);
            
            System.out.println("Manchado " + id + " quiero papelera");
            cv.encolapapelera(id, tipo, leche, cafe);
            papelera.acquire();
            sleep(500);
            cv.fincolapapelera(id, tipo, leche, cafe);

            cv.enpapelera(id, tipo, leche, cafe);
            sleep(Duration.ofSeconds((long) 1));
            cv.finpapelera(id, tipo, leche, cafe);

            cv.ensalon(id, tipo, leche, cafe);
            papelera.release();
            System.out.println("Manchado " + id + " termine");

        } catch (InterruptedException ex) {
            System.out.println("Ha llegado una interrupcion: " + ex.getMessage());
        }
    }
}
