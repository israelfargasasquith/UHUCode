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

    public CanvasPila(int alto, int ancho) {
        this.alto = alto;
        this.ancho = ancho;
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

        tmp.setFont(f);
        tmp.setColor(Color.BLACK);
        tmp.drawLine(alto/2, 0, alto/2, ancho);
        tmp.drawImage(buffer, 0, 0, null);
        //og.fillOval(20, 30, 20, 20);
        
        //og.drawString("Valor de contador 1 --> " + contadores[0], 50, 50);
        //og.setColor(Color.blue);
        //og.fillOval(20, 80, 20, 20);
        //og.drawString("Valor de contador 2 --> " + contadores[1], 50, 100);
        //og.drawImage(offscreen, 0, 0, null);

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
