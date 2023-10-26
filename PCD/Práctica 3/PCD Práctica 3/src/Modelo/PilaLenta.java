/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.CanvasPila;
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
    private CanvasPila canvas;

    public PilaLenta(int capacidad, CanvasPila canvas) {
        this.canvas = canvas;
        this.capacidad = capacidad;
        cima = -1;
        numElementos = 0;
        datos = new Object[capacidad];
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
    public synchronized void apila(Object insertar) throws java.lang.Exception {
        if (!pilaLlena()) {
            sleep(100);
            cima++;
            sleep(100);
            numElementos++;
            sleep(100);
            datos[cima] = insertar;
            canvas.actualiza(cima, numElementos, datos);
        } else {
            canvas.avisa("Error al intentar apilar el objeto: " + insertar + ", la pila esta llena");
            throw new Exception("");
        }
    }

    @Override
    public synchronized Object desapila() throws java.lang.Exception {
        if (!pilaVacia()) {
            sleep(100);
            numElementos--;
            sleep(100);
            cima--;
            sleep(100);
            canvas.actualiza(cima, numElementos, datos);
            return datos[cima + 1];
        } else {
            canvas.avisa("Error al intentar desapilar, la pila esta vacia");
            throw new Exception("");
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
