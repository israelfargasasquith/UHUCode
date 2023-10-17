/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import static java.lang.Thread.sleep;

/**
 *
 * @author 34667
 */
public class PilaLenta implements IPila {

    private int cima; //apunta a la posicion del numero mas alto
    private int capacidad;
    private int numElementos;
    private Object[] datos;

    public PilaLenta(int capacidad) {
        cima = -1;
        this.capacidad = capacidad;
        numElementos = 0;
        datos = new Object[capacidad];
    }

    public void muestraPila() {
        System.out.println("\nMostramos la pila completa a continuacion:");
        for (int j = 0; j < numElementos; j++) {
            System.out.println("Datos[" + j + "] = " + datos[j]);
        }
    }

    private boolean pilaLlena() { //true si esta llena
        return numElementos == capacidad;
    }

    private boolean pilaVacia() { //true si esta vacia
        return numElementos == 0;
    }

    @Override
    public int getNumElementos() {
        return numElementos;
    }

    @Override
    public void apila(Object insertar) throws java.lang.Exception {
        if (!pilaLlena()) {
            sleep(100);
            cima++;
            sleep(100);
            numElementos++;
            sleep(100);
            datos[cima] = insertar;
        } else {
            throw new Exception("Error al intentar apilar el objeto: " + insertar + ", la pila esta llena");
        }
    }

    @Override
    public Object desapila() throws java.lang.Exception {
        if (!pilaVacia()) {
            sleep(100);
            numElementos--;
            sleep(100);
            cima--;
            sleep(100);
            return datos[cima+1];
        } else {
            throw new Exception("Error al intentar desapilar, la pila esta vacia");
        }

    }

    @Override
    public Object primero() throws java.lang.Exception {
        if (!pilaVacia()) {
            return datos[cima];
        } else {
            throw new Exception("Error en funcion primero: la pila esta vacia");
        }

    }

}
