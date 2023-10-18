/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_frame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author pedro
 */
public class VistaCanvas extends Canvas {

    int[] contadores = {0, 0};

    public VistaCanvas(int ancho, int alto) {
        setSize(new Dimension(alto, ancho));
        this.setBackground(Color.cyan);
        repaint();
    }

    /**
     *
     * @param datos
     */
    public void representa(int[] datos) {
        contadores = datos;
        repaint();
    }

    @Override
    public void paint(Graphics g) {

        // Se crea un buffer intermedio para que montar la salida completa
        // y luego pintarla de una vez, evitando el parpadeo
        Image offscreen = createImage(getWidth(), getHeight());
        Graphics og = offscreen.getGraphics();

        Font f1 = new Font("Helvetica", Font.ITALIC + Font.BOLD, 20);

        og.setFont(f1);
        og.setColor(Color.red);
        og.drawString("PILA", 320, 530);
        int capacidad = 3;
        int y = 500;
        int yLetras = 485;
        //Pinta cuadrado de informacion

        while (capacidad > 0) {
            og.drawLine(250, y, 450, y);//Linea horizontal abajo
            og.drawLine(250, y, 250, y - 50);//Linea vertical izquierda
            og.drawLine(450, y, 450, y - 50);//Linea vertical derecha
            y -= 50;
            og.drawLine(250, y, 450, y);//Linea horizontal arriba
            og.drawString("Prueba", 320, yLetras);
            capacidad--;
            yLetras -= 50;
        }
        og.drawString("Cima ==> ", 155, 485);
        Font f2 = new Font("Helvetica", Font.ITALIC + Font.BOLD, 15);
        og.setFont(f2);

        og.drawLine(550, 425, 800, 425);//Linea horizontal abajo
        og.drawLine(550, 325, 800, 325);//Linea horizontal arriba
        og.drawLine(550, 425, 550, 325);//Linea vertical izquierda
        og.drawLine(800, 425, 800, 325);//Linea vertical derecha
        og.drawString("* Panel de informacion de la pila *", 560, 350);
        og.drawString("1. NumElementos => ", 560, 380);
        og.drawString("2. Cima => ", 560, 410);
        og.drawString("Mensaje: ", 560, 445);
//        og.drawLine(250, 450, 450, 450);//Linea horizontal arriba
//        og.drawLine(250, 500, 250, 450);//Linea vertical izquierda
//        og.drawLine(450, 500, 450, 450);//Linea vertical derecha

//        og.drawLine(250, 450, 450, 450); Una caja justo encima
//        og.drawLine(250, 400, 450, 400);
//        og.drawLine(250, 450, 250, 400);
//        og.drawLine(450, 450, 450, 400);
        // og.drawLine(0, 0, 650, 400);
        //og.drawString("Valor de contador 1 --> " + contadores[0], 50, 50);
        //og.setColor(Color.blue);
        //og.fillOval(20, 80, 20, 20);
        //og.drawString("Valor de contador 2 --> " + contadores[1], 50, 100);
        og.drawImage(offscreen, 0, 0, null);

        g.drawImage(offscreen, 0, 0, null);
    }

    /* El update original del canvas, borra el canvas y llama a paint. Si queremos 
     sobreescribir  lo que hay pintado, sobrecargamos update y hacemos que llame 
     paint. Así no borra lo anterior, y no se produce parpadeo
     */
    @Override
    public void update(Graphics g) {
        paint(g);
    }

}
