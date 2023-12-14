/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import static java.lang.Thread.sleep;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 *
 * @author 34667
 */
public class ClienteEfectivo implements Callable<Integer> {

    private Tienda t;
    private Random rand;
    private int id;

    public ClienteEfectivo(Tienda t, int id) {
        this.t = t;
        this.rand = new Random(System.nanoTime());
        this.id = id;
    }

    @Override
    public Integer call() throws InterruptedException {
        int tiempo = rand.nextInt(1, 3)*1000;

        System.out.println("Hilo efectivo " + id + " y acabo de nacer");
        System.out.println("Hilo efectivo " + id + " esperando a pagar");
        t.entraEfectivo();
        System.out.println("Hilo efectivo " + id + " y voy a pagar");
        sleep(tiempo); //pagando
        t.saleEfectivo();
        System.out.println("Hilo efectivo " + id + " y he salido de pagar, ya acabe");
        
        return tiempo;
    }

}
