/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author 34667
 */
public class Punto {

    private double x;
    private double y;
    private int id;
    private static int numPoints = 0;

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
        this.id = ++numPoints;
    }

    public Punto(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
        numPoints++;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getId() {
        return id;
    }
    
}
