/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemplothpool;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author 34667
 */
public class EjemploThPool {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService poolRetorno = Executors.newFixedThreadPool(3);
        ExecutorService poolSinRetorno = Executors.newFixedThreadPool(3);
        ArrayList<Future<Integer>> retornoSuma = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            retornoSuma.add(poolRetorno.submit(new ConRetorno(i + 10)));
            poolSinRetorno.submit(new SinRetorno(i));
            // pool.submit(new SinRetorno(i)); //Pone la tarea t en la cola de tareas del threadPool
            //res[i] = pool.submit(new ConRetorno(10 + i)); //Estas tareas devuelven un Integer y se pueden mezclar en el pool
            //Future indica donde va a poner el resultado cuando acabe el hilo, el resultado se recupera con res.get, da igual que lo hagamos 
            //antes o despues del shutdown
        }

//         ExecutorService pool = Executors.newFixedThreadPool(3); esto sin pruebas
//        ArrayList<Future<Integer>> res = new ArrayList<>();
//        for (int i = 0; i < 15; i++) {
//            res.add(pool.submit(new ConRetorno(i+10)));
//            // pool.submit(new SinRetorno(i)); //Pone la tarea t en la cola de tareas del threadPool
//            //res[i] = pool.submit(new ConRetorno(10 + i)); //Estas tareas devuelven un Integer y se pueden mezclar en el pool
//            //Future indica donde va a poner el resultado cuando acabe el hilo, el resultado se recupera con res.get, da igual que lo hagamos 
//            //antes o despues del shutdown
//        }
//  pool.shutdown();
//        for (int i = 0; i < res.size(); i++) {
//            System.out.println("Size->"+res.size());
//            //System.out.println("El resultado es " + res[i].get());
//            System.out.println("El resultado es " + res.get(i).get());
//        }
        poolRetorno.shutdown();
        poolSinRetorno.shutdown();
        for (int i = 0; i < retornoSuma.size(); i++) {
            System.out.println("Size->" + retornoSuma.size());
            //System.out.println("El resultado es " + res[i].get());
            System.out.println("El resultado es " + retornoSuma.get(i).get());
        }
    }

}

class SinRetorno implements Runnable {

    private int id;

    public SinRetorno(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Soy la tarea " + id + " y la ejecuta el hilo con id " + Thread.currentThread().getName());
            try {
                sleep(2000);
            } catch (Exception e) {
            }
        }
    }
}

class ConRetorno implements Callable<Integer> {

    private int id;

    public ConRetorno(int id) {
        this.id = id;
    }

    @Override
    public Integer call() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Soy la tarea " + id + " y la ejecuta el hilo con id " + Thread.currentThread().getName());
            try {
                sleep(2000);
            } catch (Exception e) {
            }
        }
        return id * 10;
    }
}
