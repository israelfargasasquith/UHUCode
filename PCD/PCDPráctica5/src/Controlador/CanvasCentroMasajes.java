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
public class CanvasCentroMasajes extends Canvas {

    private int alto, ancho;

    public CanvasCentroMasajes(int alto, int ancho) {
        this.alto = alto;
        this.ancho = ancho;
        setBackground(Color.LIGHT_GRAY);
        setSize(alto, ancho);
        setVisible(true);
        repaint();
    }

    public void actualiza() {

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Image buffer = createImage(alto, ancho);
        Graphics tmp = buffer.getGraphics();

        Font f1 = new Font("Helvetica", Font.BOLD, 20);

        tmp.setFont(f1);
        tmp.setColor(Color.black);
        tmp.drawRect(20, 100, 400, 100); //RepresentaColaMasaje
        tmp.fillRect(20, 100, 400, 100);
        tmp.drawRect(20, 350, 400, 100); //ColaRehabilita
        tmp.fillRect(20, 100, 400, 100);
        
//        tmp.drawString("PILA", 20, 530);
//        int y = 500;
//        int yLetras = 485;
//        int pos = 0;
//        int alturaPila = capacidad;
//        while (alturaPila > 0) {
//            tmp.drawLine(130, y, 330, y);//Linea horizontal abajo
//            tmp.drawLine(130, y, 130, y - 50);//Linea vertical izquierda
//            tmp.drawLine(330, y, 330, y - 50);//Linea vertical derecha
//            y -= 50;
//            tmp.drawLine(130, y, 330, y);//Linea horizontal arriba
//            if (pos > cima) {
//                tmp.drawString("Vacío", 210, yLetras);
//            } else {
//                tmp.drawString(datos[pos].toString(), 210, yLetras);
//
//            }
//
//            tmp.drawString("" + pos, 340, yLetras);
//
//            pos++;
//            alturaPila--;
//            yLetras -= 50;
//        }
//        if (cima != -1) {
//            tmp.drawString("Cima => ", 25, 485 - 50 * cima);
//        }
//        Font f2 = new Font("Arial", Font.BOLD, 15);
//        tmp.setFont(f2);
//
//        //Pinta cuadrado de informacion
//        tmp.drawLine(400, 425, 650, 425);//Linea horizontal abajo
//        tmp.drawLine(400, 325, 650, 325);//Linea horizontal arriba
//        tmp.drawLine(400, 425, 400, 325);//Linea vertical izquierda
//        tmp.drawLine(650, 425, 650, 325);//Linea vertical derecha
//        tmp.drawString("* Panel de informacion de la pila *", 410, 350);
//        tmp.drawString("1. NumElementos => " + numElementos, 410, 380);
//        tmp.drawString("2. Cima => " + cima, 410, 410);
//        tmp.drawString("Mensaje: " + mensaje, 25, 550);
//
//        tmp.drawImage(buffer, 0, 0, null); //pintamos la imagen de buffer en el frame para evitar parpadeos
//        g.drawImage(buffer, 0, 0, null);
        tmp.drawImage(buffer, 0, 0, null); //pintamos la imagen de buffer en el frame para evitar parpadeos
        g.drawImage(buffer, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

}
