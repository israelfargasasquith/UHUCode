/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ClienteMasaje;
import Modelo.ClienteRehabilita;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author 34667
 */
public class CanvasCentroMasajes extends Canvas {

    private int alto, ancho;
    private boolean masajistaLibre, fisioterapeutaLibre, vestuarioLibre;
    private ArrayList<Thread> lClientes;
    private BufferedImage clienteMasaje, clienteRehabilitacion, masajista, fisio;
//    private ArrayList<ClienteMasaje> lMasaje;
//    private ArrayList<ClienteRehabilita> lRehabilita; //Puede que no sean necesarios

    public CanvasCentroMasajes(int alto, int ancho, ArrayList<Thread> lClientes) throws IOException {//,ArrayList<ClienteMasaje> lMasaje, ArrayList<ClienteRehabilita> lRehabilita ) {
        this.alto = alto;
        this.ancho = ancho;
        this.lClientes = lClientes;
        String pathBase = "C:\\Users\\34667\\Desktop\\Prácticas\\PCD\\Práctica 5\\PCDPráctica5\\src\\Imagenes";
        clienteMasaje = ImageIO.read(new File(pathBase + "\\clienteMasaje.jpg"));
        clienteRehabilitacion = ImageIO.read(new File(pathBase + "\\dolorEspalda.jpg"));
        masajista = ImageIO.read(new File(pathBase + "\\masaje.png"));
        fisio = ImageIO.read(new File(pathBase + "\\fisio.jpg"));
//        this.lMasaje = lMasaje;
//        this.lRehabilita = this.lRehabilita;
        setBackground(Color.LIGHT_GRAY);
        setSize(alto, ancho);
        setVisible(true);
        repaint();
    }

    public void actualiza(boolean masajistaLibre, boolean fisioterapeutaLibre, boolean vestuarioLibre, long idThread) {
        this.masajistaLibre = masajistaLibre;
        this.fisioterapeutaLibre = fisioterapeutaLibre;
        this.vestuarioLibre = vestuarioLibre;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Image buffer = createImage(alto, ancho);
        Graphics tmp = buffer.getGraphics();

        Font f1 = new Font("Helvetica", Font.BOLD, 20);

        tmp.setFont(f1);
        tmp.drawString("Cola de Masajes:", 20, 95);
        tmp.drawString("Cola de Fisio:", 20, 445);
        tmp.drawString("Masajista:", 425, 95);
        tmp.drawString("Fisioterapeuta:", 425, 420);
        tmp.drawString("Vestuario:", 650, 195);
        tmp.drawString("Acabados:", 20, 275);

        tmp.setColor(Color.WHITE);
        tmp.fillRect(20, 100, 400, 100); //Cola masajes
        tmp.fillRect(20, 450, 400, 100); //Cola fisio
        tmp.fillRect(425, 100, 200, 125); //Masajista
        tmp.fillRect(425, 425, 200, 125); //Fisio
        tmp.fillRect(650, 200, 250, 250); //Vestuario
        tmp.fillRect(20, 280, 500, 100); //Acabados

        tmp.drawImage(fisio, 485, 110, 35, 100, this);

        for (Thread lCliente : lClientes) {
            if (lCliente.getClass().equals(ClienteMasaje.class)) {

            } else if (lCliente.getClass().equals(ClienteRehabilita.class)) {
                //pintar uno que quiere rehabilita
            }
        }

        tmp.drawImage(buffer, 0, 0, null); //pintamos la imagen de buffer en el frame para evitar parpadeos
        g.drawImage(buffer, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

}
