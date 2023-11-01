/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author 34667
 */
public class ParPuntos {

    private Punto a;
    private Punto b;
    private double distancia;

    
    public ParPuntos(){
        distancia = -1;
    }
    public ParPuntos(Punto a, Punto b) {
        this.a = a;
        this.b = b;
        distancia = Math.sqrt(Math.pow((a.getX() - b.getX()), 2.0) + Math.pow((a.getY() - b.getY()), 2.0));
    }

    public Punto getA() {
        return a;
    }

    public void setA(Punto a) {
        this.a = a;
    }

    public Punto getB() {
        return b;
    }

    public void setB(Punto b) {
        this.b = b;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    

}