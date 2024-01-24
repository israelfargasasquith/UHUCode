package Servidor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author israel
 */
public class CanvasTienda extends Canvas {

    private ArrayList<Integer> colaTarjeta;
    private ArrayList<Integer> colaEfectivo;
    private ArrayList<Integer> colaAcabadosEfectivo;
    private ArrayList<Integer> colaAcabadosTarjeta;
    private ArrayList<Integer> cajerosTarjeta;
    private Integer cajeroEfectivo;
    private String[] cajeros;
    private int posEfectivo;

    public CanvasTienda(int alto, int ancho) {
        super.setSize(alto, ancho);
        super.setBackground(Color.WHITE);
        colaTarjeta = new ArrayList<>();
        colaEfectivo = new ArrayList<>();
        colaAcabadosEfectivo = new ArrayList<>();
        colaAcabadosTarjeta = new ArrayList<>();
        cajerosTarjeta = new ArrayList<>();
        cajeroEfectivo = -1;
//        cajeros = new String[4];
//        posEfectivo = -1;
    }

    @Override
    public void update(Graphics g) {
        paint(g);

    }

    @Override
    public void paint(Graphics g) {

        Image imagen = createImage(getWidth(), getHeight());
        Font f1 = new Font("Arial", Font.BOLD, 15);
        Font f2 = new Font("Arial", Font.BOLD, 10);
        Graphics gbuf = imagen.getGraphics();

        gbuf.setFont(f1);
        gbuf.setColor(Color.cyan);

        int posTarjeta[] = {30, 200};
        int posEfectivo[] = {230, 200};
        int posCajeros[] = {30, 40};
        int posAcabados[] = {450, 200};
        int posAcabadosEfectivo[] = {550, 200};

        gbuf.fillRect(posTarjeta[0], posTarjeta[1], 80, 400);
        gbuf.fillRect(posEfectivo[0], posEfectivo[1], 80, 400);
        gbuf.fillRect(posAcabados[0], posAcabados[1], 200, 400);
        gbuf.fillRect(posCajeros[0], posCajeros[1], 390, 80);
        gbuf.setColor(Color.gray);
        for (int i = 0; i < 4; i++) { //separamos los cajeros
            gbuf.drawRect(posCajeros[0] + (i * 100), posCajeros[1], 90, 80);
        }
        gbuf.setColor(Color.black);
        gbuf.drawLine(posAcabados[0] + 100, posAcabados[1], posAcabados[0] + 100, posAcabados[1] + 400);
        gbuf.drawString("Cajero 1:", posCajeros[0], posCajeros[1]);
        gbuf.drawString("Cajero 2:", posCajeros[0] + 100, posCajeros[1]);
        gbuf.drawString("Cajero 3:", posCajeros[0] + 200, posCajeros[1]);
        gbuf.drawString("Cajero 4:", posCajeros[0] + 300, posCajeros[1]);
        gbuf.drawString("Cola Efectivo:", posEfectivo[0], posEfectivo[1]);
        gbuf.drawString("Cola Tarjeta:", posTarjeta[0], posTarjeta[1]);
        gbuf.drawString("Acabados", posAcabados[0]+60, posAcabados[1]-30);
        gbuf.drawString("Tarjeta               Efectivo:", posAcabados[0], posAcabados[1]);

        gbuf.setColor(Color.BLUE);
        int iEfectivo = 0;
        for (Integer efectivo : colaEfectivo) {
            gbuf.drawString(efectivo.toString(), posEfectivo[0], posEfectivo[1] + (iEfectivo * 30) + 30);
            gbuf.drawOval(posEfectivo[0], posEfectivo[1] + (iEfectivo * 30) + 30, 10, 10); //cambiar por imagenes
            iEfectivo++;
        }
        if (!cajeroEfectivo.equals(-1)) {
            gbuf.drawString(cajeroEfectivo.toString(), posCajeros[0] + 340, posCajeros[1] + 40);
            gbuf.drawOval(posCajeros[0] + 340, posCajeros[1] + 40, 10, 10); //cambiar por imagenes
        }

        gbuf.setColor(Color.red);
        int iTarjeta = 0;
        for (Integer tarjeta : colaTarjeta) {
            gbuf.drawString(tarjeta.toString(), posTarjeta[0], posTarjeta[1] + (iTarjeta * 30) + 30);
            gbuf.drawOval(posTarjeta[0], posTarjeta[1] + (iTarjeta * 30) + 30, 10, 10); //cambiar por imagenes
            iTarjeta++;
        }

        int iCajeros = 0;
        for (Integer tarjeta : cajerosTarjeta) {
            gbuf.drawString(tarjeta.toString(), posCajeros[0] + 40 + (iCajeros * 100), posCajeros[1] + 40);
            gbuf.drawOval(posCajeros[0] + 40 + (iCajeros * 100), posCajeros[1] + 40, 10, 10); //cambiar por imagenes
            iCajeros++;
        }

        gbuf.setColor(Color.black);
        int iAcabados = 0;
        int iAcabados2 = 0;
        for (Integer acabado : colaAcabadosTarjeta) {
            if (iAcabados < 19) {
                gbuf.drawString(acabado.toString(), posAcabados[0], posAcabados[1] + (iAcabados * 20) + 30);

            } else {
                gbuf.drawString(acabado.toString(), posAcabados[0] + 30, posAcabados[1] + (iAcabados2 * 20) + 30);
                iAcabados2++;
            }
            iAcabados++;
        }
        int iAcabadosEfectivo = 0;
        int iAcabadosEfectivo2 = 0;
        for (Integer acabado : colaAcabadosEfectivo) {
            if (iAcabadosEfectivo < 19) {
                gbuf.drawString(acabado.toString(), posAcabadosEfectivo[0], posAcabadosEfectivo[1] + (iAcabadosEfectivo * 20) + 30);

            } else {
                gbuf.drawString(acabado.toString(), posAcabadosEfectivo[0] + 30, posAcabadosEfectivo[1] + (iAcabadosEfectivo2 * 20) + 30);
                iAcabadosEfectivo2++;
            }
            iAcabadosEfectivo++;
        }

        g.drawImage(imagen, 0, 0, this);

    }

    public void pintaEsperaTarjeta(int id) {

        colaTarjeta.add(id);
        repaint();

    }

    public void borraEsperaTarjeta(int id) {
        colaTarjeta.remove(Integer.valueOf(id));
        repaint();
    }

    public void pintaEntraTarjeta(int id) {
        cajerosTarjeta.add(id);
        repaint();
    }

    public void borraTarjetaPagando(int id) {
        colaAcabadosTarjeta.add(id);
        cajerosTarjeta.remove(Integer.valueOf(id));
        repaint();
    }

    public void pintaEsperaEfectivo(int id) {
        colaEfectivo.add(id);
        repaint();
    }

    public void borraEsperaEfectivo(int id) {
        colaEfectivo.remove(Integer.valueOf(id));
        repaint();
    }

    public void pintaEntraEfectivo(int id) {
        cajeroEfectivo = id;
        repaint();
    }

    public void borraEfectivoPagando(int id) {
        colaAcabadosEfectivo.add(cajeroEfectivo);
        cajeroEfectivo = -1;
        repaint();
    }

}
