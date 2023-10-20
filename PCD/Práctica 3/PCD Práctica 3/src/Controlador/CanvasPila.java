/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author 34667
 */
public class CanvasPila extends Canvas {

    private int alto;
    private int ancho;
    private int capacidad;
    private int cima;
    private int numElementos;
    private Object[] datos;
    private String mensaje;

    public CanvasPila(int alto, int ancho, int capacidad) {
        this.alto = alto;
        this.ancho = ancho;
        this.capacidad = capacidad;
        cima = -1;
        mensaje = "";
        numElementos = 0;
        setBackground(Color.LIGHT_GRAY);
        setSize(alto, ancho);
        setVisible(true);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Font f = new Font("Helvetica", Font.ITALIC + Font.BOLD, 20);
        Image buffer = createImage(alto, ancho);
        Graphics tmp = buffer.getGraphics();

        Font f1 = new Font("Helvetica", Font.ITALIC + Font.BOLD, 20);

        tmp.setFont(f1);
        tmp.setColor(Color.red);
        tmp.drawString("PILA", 320, 530);
        int y = 500;
        int yLetras = 485;
        int pos = 0;
        while (capacidad > 0) {
            tmp.drawLine(250, y, 450, y);//Linea horizontal abajo
            tmp.drawLine(250, y, 250, y - 50);//Linea vertical izquierda
            tmp.drawLine(450, y, 450, y - 50);//Linea vertical derecha
            y -= 50;
            tmp.drawLine(250, y, 450, y);//Linea horizontal arriba
            tmp.drawString("Vacío", 320, yLetras);
            tmp.drawString("" + pos, 460, yLetras);

            pos++;
            capacidad--;
            yLetras -= 50;
        }
        //tmp.drawString("Cima => ", 155, 485);
        Font f2 = new Font("Helvetica", Font.ITALIC + Font.BOLD, 15);
        tmp.setFont(f2);

        //Pinta cuadrado de informacion
        tmp.drawLine(550, 425, 800, 425);//Linea horizontal abajo
        tmp.drawLine(550, 325, 800, 325);//Linea horizontal arriba
        tmp.drawLine(550, 425, 550, 325);//Linea vertical izquierda
        tmp.drawLine(800, 425, 800, 325);//Linea vertical derecha
        tmp.drawString("* Panel de informacion de la pila *", 560, 350);
        tmp.drawString("1. NumElementos => "+ numElementos, 560, 380);
        tmp.drawString("2. Cima => "+ cima, 560, 410);
        tmp.drawString("Mensaje: " +mensaje, 560, 445);

        tmp.drawImage(buffer, 0, 0, null);

        g.drawImage(buffer, 0, 0, null);
        g.drawImage(buffer, 0, 0, null);

    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void avisa(String mensaje) {

    }

    public void representa(Object[] buff, int cima, int numEle) {

    }
}
